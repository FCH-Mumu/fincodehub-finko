package com.fincodehub.finko.user.biz.runner;

import cn.hutool.core.collection.CollUtil;
import com.fincodehub.finko.user.biz.constant.RedisKeyConstants;
import com.fincodehub.finko.user.biz.domain.PermissionDO;
import com.fincodehub.finko.user.biz.domain.RoleDO;
import com.fincodehub.finko.user.biz.domain.RolePermissionDO;
import com.fincodehub.finko.user.biz.mapper.PermissionDOMapper;
import com.fincodehub.finko.user.biz.mapper.RoleDOMapper;
import com.fincodehub.finko.user.biz.mapper.RolePermissionDOMapper;
import com.finko.framework.common.util.JsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @title PushRolePermissions2RedisRunner
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/7/14 15:51
 * @description <TODO description class purpose>
 */
@Component
@Slf4j
public class PushRolePermissions2RedisRunner implements ApplicationRunner {
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private PermissionDOMapper permissionDOMapper;
    @Resource
    private RolePermissionDOMapper rolePermissionDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void run(ApplicationArguments args) {
        log.info("==> 服务启动，开始同步角色权限数据到 Redis 中...");

        try{
            // 是否能同步：原子操作，只有在键 PUSH_PERMISSION_FLAG 不存在时，才会设置该键的值为"1",并设置过期时间为1天
            System.out.println("rolePermissionsKey=11==="+ RedisKeyConstants.buildPushPermissionFlagKey());
            boolean canPushed = redisTemplate.opsForValue().setIfAbsent(RedisKeyConstants.buildPushPermissionFlagKey(), "1", 1, TimeUnit.DAYS);
            if (!canPushed){
                log.info("==> 服务启动，角色权限数据已同步，不再同步...");
                return;
            }
            // 查询所有角色
            List<RoleDO> roleDOS = roleDOMapper.selectEnabledRoleList();
            if (CollUtil.isNotEmpty(roleDOS)){
                // 获取所有的角色ID
                List<Long> roleIds = roleDOS.stream().map(RoleDO::getId).toList();
                log.info("==> 服务启动，查询到角色：{} 个", roleDOS.size());
                // 批量查询角色权限关系
                List<RolePermissionDO> rolePermissionIds = rolePermissionDOMapper.selectByRoleIds(roleIds);
                log.info("==> 服务启动，查询到角色权限关系：{} 个", rolePermissionIds.size());
                // 按角色ID分组，每个角色ID对应多个权限ID
                Map<Long, List<Long>> roleIdPermissionIdsMap = rolePermissionIds.stream().collect(
                        Collectors.groupingBy(RolePermissionDO::getRoleId,
                                Collectors.mapping(RolePermissionDO::getPermissionId, Collectors.toList())));
                // 查询APP端所有权限
                List<PermissionDO> permissionDOS = permissionDOMapper.selectEnabledPermissionList();
                // 权限ID - 权限DO
                Map<Long, PermissionDO> permissionIdDOMap = permissionDOS.stream().collect(Collectors.toMap(PermissionDO::getId, permissionDO -> permissionDO));

                // 组织 角色ID-权限关系
                HashMap<String, List<String>> roleKeyPermissionDOMap = Maps.newHashMap();

                // 遍历所有角色
                roleDOS.forEach(roleDO -> {
                    // 当前角色 ID
                    Long roleId = roleDO.getId();
                    // 当前角色 roleKey
                    String roleKey = roleDO.getRoleKey();
                    log.info("==> 服务启动，开始同步角色：{} 的权限...", roleKey);
                    // 当前角色ID对应的权限ID
                    List<Long> permissionIds = roleIdPermissionIdsMap.get(roleId);
                    if (CollUtil.isNotEmpty(permissionIds)){
                        List<String> permissionKeys = Lists.newArrayList();
                        permissionIds.forEach(permissionId -> {
                            // 根据权限ID获取具体权限DO对象
                            PermissionDO permissionDO = permissionIdDOMap.get(permissionId);
                            if (permissionDO!=null){
                                permissionKeys.add(permissionDO.getPermissionKey());
                            }
                        });
                        roleKeyPermissionDOMap.put(roleKey, permissionKeys);
                    }
                });
                // 同步至Redis, 方便后续网关查询redis,用于鉴权
                roleKeyPermissionDOMap.forEach((roleKey, permissions) -> {
                    // 角色权限关系 Redis Key
                    String rolePermissionsKey = RedisKeyConstants.buildRolePermissionsKey(roleKey);
                    System.out.println("rolePermissionsKey===="+rolePermissionsKey);
                    redisTemplate.opsForValue().set(rolePermissionsKey, JsonUtils.toJsonString( permissions));
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("==> 服务启动，成功同步角色权限数据到 Redis 中...");
    }
}