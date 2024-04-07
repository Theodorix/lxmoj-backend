package com.yupi.lxmoj.controller;


import com.yupi.lxmoj.common.BaseResponse;
import com.yupi.lxmoj.common.ErrorCode;
import com.yupi.lxmoj.common.ResultUtils;
import com.yupi.lxmoj.exception.BusinessException;
import com.yupi.lxmoj.model.dto.postthumb.PostThumbAddRequest;
import com.yupi.lxmoj.model.entity.User;
import com.yupi.lxmoj.service.PostThumbService;
import com.yupi.lxmoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 */
@RestController
@RequestMapping("/post_thumb")
@Slf4j
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Integer> doPostThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                             HttpServletRequest request) {

        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        log.info("帖子ID：{}", postId);
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取点赞状态
     **/
    @GetMapping("get/post_thumb/status")
    public BaseResponse<Long> getPost_thumbStatus(long postId, HttpServletRequest request) {
        log.info("获取帖子点赞状态：t帖子ID：{}, HTTP请求信息：{}", postId, request);
        if (postId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        // 从数据库中查询信息
        Long post_thumbStatus = postThumbService.getPost_thumbStatus(postId, userId);
        return ResultUtils.success(post_thumbStatus);
    }

}
