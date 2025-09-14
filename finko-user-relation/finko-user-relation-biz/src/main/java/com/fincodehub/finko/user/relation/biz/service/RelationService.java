package com.fincodehub.finko.user.relation.biz.service;

import com.fincodehub.finko.user.relation.biz.model.vo.FindFansListReqVO;
import com.fincodehub.finko.user.relation.biz.model.vo.FindFansUserRspVO;
import com.fincodehub.finko.user.relation.biz.model.vo.FindFollowingListReqVO;
import com.fincodehub.finko.user.relation.biz.model.vo.FindFollowingUserRspVO;
import com.fincodehub.finko.user.relation.biz.model.vo.FollowUserReqVO;
import com.fincodehub.finko.user.relation.biz.model.vo.UnfollowUserReqVO;
import com.finko.framework.common.response.PageResponse;
import com.finko.framework.common.response.ResponseObject;

/**
 * @title RelationService
 * @author FCH丨木木
 * @version 1.0.0
 * @create 2025/9/13 20:56
 * @description 关注业务
 */
public interface RelationService {
    /**
     * 关注用户
     * @param followUserReqVO
     * @return
     */
    ResponseObject<?> follow(FollowUserReqVO followUserReqVO);

    /**
     * 取关用户
     * @param unfollowUserReqVO
     * @return
     */
    ResponseObject<?> unfollow(UnfollowUserReqVO unfollowUserReqVO);

    /**
     * 查询关注列表
     * @param findFollowingListReqVO
     * @return
     */
    PageResponse<FindFollowingUserRspVO> findFollowingList(FindFollowingListReqVO findFollowingListReqVO);

    /**
     * 查询关注列表
     * @param findFansListReqVO
     * @return
     */
    PageResponse<FindFansUserRspVO> findFansList(FindFansListReqVO findFansListReqVO);
}
