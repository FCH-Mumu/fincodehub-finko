package com.fincodehub.finko.user.relation.biz.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.user.relation.biz.domain.dataobject.Fans;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户粉丝表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-13
 */
@Mapper
public interface FansMapper extends BaseMapper<Fans> {
  @Delete("delete from sys_fans where user_id = #{userId} and fans_user_id = #{fansUserId}")
  int deleteByUserIdAndFansUserId(
      @Param("userId") Long userId, @Param("fansUserId") Long fansUserId);

  /**
   * 查询记录总数
   *
   * @param userId
   * @return
   */
  @Select("select count(1) from sys_fans where user_id = #{userId}")
  long selectCountByUserId(Long userId);

  /**
   * 分页查询
   * @param userId
   * @param offset
   * @param limit
   * @return
   */
  @Select("select id, user_id, fans_user_id, create_time from sys_fans where user_id = #{userId} order by create_time desc limit #{offset}, #{limit}")
  List<Fans> selectPageListByUserId(
      @Param("userId") Long userId, @Param("offset") long offset, @Param("limit") long limit);

  /**
   * 查询最新关注的 5000 位粉丝
   * @param userId
   * @return
   */
  @Select("select id, user_id, fans_user_id, create_time from sys_fans  where user_id = #{userId} order by create_time desc limit 5000")
  List<Fans> select5000FansByUserId(Long userId);
}
