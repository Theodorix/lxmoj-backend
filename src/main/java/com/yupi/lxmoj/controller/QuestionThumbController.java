package com.yupi.lxmoj.controller;

import com.yupi.lxmoj.common.BaseResponse;
import com.yupi.lxmoj.common.ErrorCode;
import com.yupi.lxmoj.common.ResultUtils;
import com.yupi.lxmoj.exception.BusinessException;
import com.yupi.lxmoj.model.dto.questionthumb.QuestionThumbAddRequest;
import com.yupi.lxmoj.model.entity.User;
import com.yupi.lxmoj.service.QuestionThumbService;
import com.yupi.lxmoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目点赞接口
 */
@RestController
@RequestMapping("/question_thumb")
@Slf4j
public class QuestionThumbController {

    @Resource
    private QuestionThumbService questionThumbService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionThumbAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @CacheEvict(cacheNames = {"questionByPage", "questionById"}, allEntries = true)
    @PostMapping("/")
    public BaseResponse<Integer> doQuestionThumb(@RequestBody QuestionThumbAddRequest questionThumbAddRequest,
                                                 HttpServletRequest request) {

        if (questionThumbAddRequest == null || questionThumbAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionThumbAddRequest.getQuestionId();
        log.info("题目ID：{}", questionId);
        int result = questionThumbService.doQuestionThumb(questionId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取点赞状态
     **/
    @GetMapping("get/question_thumb/status")
    public BaseResponse<Long> getQuestion_thumbStatus(long questionId, HttpServletRequest request) {
        log.info("获取帖子点赞状态：t帖子ID：{}, HTTP请求信息：{}", questionId, request);
        if (questionId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        // 从数据库中查询信息
        Long question_thumbStatus = questionThumbService.getQuestion_thumbStatus(questionId, userId);
        return ResultUtils.success(question_thumbStatus);
    }

}
