package com.fincodehub.finko.user.relation.biz.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fincodehub.finko.user.relation.biz.domain.dataobject.Following;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户关注表 Mapper 接口
 * </p>
 *
 * @author FCH丨木木
 * @since 2025-09-13
 */
@Mapper
public interface FollowingMapper extends BaseMapper<Following> {
  @Select("select id,user_id, following_user_id, create_time  from sys_following where user_id = #{userId}")
  List<Following> selectByUserId(Long userId);

  @Delete("delete from sys_following  where user_id = #{userId} and following_user_id = #{unfollowUserId}")
  int deleteByUserIdAndFollowingUserId(@Param("userId") Long userId, @Param("unfollowUserId") Long unfollowUserId);

  /**
   * 查询记录总数
   *
   * @param userId
   * @return
   */
  @Select("select count(1) from sys_following where user_id = #{userId}")
  long selectCountByUserId(Long userId);

  /**
   * 分页查询
   * @param userId
   * @param offset
   * @param limit
   * @return
   */
  @Select("select following_user_id from sys_following  where user_id = #{userId} order by create_time desc limit #{offset}, #{limit}")
  List<Following> selectPageListByUserId(
      @Param("userId") Long userId, @Param("offset") long offset, @Param("limit") long limit);

  /**
   * 查询关注用户列表
   * @param userId
   * @return
   */
  @Select(" select following_user_id, create_time from sys_following where user_id = #{userId} limit 1000")
  List<Following> selectAllByUserId(Long userId);


}
