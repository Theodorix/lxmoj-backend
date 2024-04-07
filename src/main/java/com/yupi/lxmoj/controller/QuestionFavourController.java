package com.yupi.lxmoj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.lxmoj.common.BaseResponse;
import com.yupi.lxmoj.common.ErrorCode;
import com.yupi.lxmoj.common.ResultUtils;
import com.yupi.lxmoj.exception.BusinessException;
import com.yupi.lxmoj.exception.ThrowUtils;
import com.yupi.lxmoj.model.dto.question.QuestionQueryRequest;
import com.yupi.lxmoj.model.dto.questionfavour.QuestionFavourAddRequest;
import com.yupi.lxmoj.model.dto.questionfavour.QuestionFavourQueryRequest;
import com.yupi.lxmoj.model.entity.Question;
import com.yupi.lxmoj.model.entity.User;
import com.yupi.lxmoj.model.vo.QuestionVO;
import com.yupi.lxmoj.service.QuestionFavourService;
import com.yupi.lxmoj.service.QuestionService;
import com.yupi.lxmoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目收藏接口
 */
@RestController
@RequestMapping("/question_favour")
@Slf4j
public class QuestionFavourController {

    @Resource
    private QuestionFavourService questionFavourService;

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    /**
     * 收藏 / 取消收藏
     *
     * @param questionFavourAddRequest
     * @param request
     * @return resultNum 收藏变化数
     */
    @CacheEvict(cacheNames = {"questionByPage", "questionById"}, allEntries = true)
    @PostMapping("/")
    public BaseResponse<Integer> doQuestionFavour(@RequestBody QuestionFavourAddRequest questionFavourAddRequest,
                                                  HttpServletRequest request) {
        if (questionFavourAddRequest == null || questionFavourAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能操作
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionFavourAddRequest.getQuestionId();
        int result = questionFavourService.doQuestionFavour(questionId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取我的收藏的题目列表
     *
     * @param questionQueryRequest
     * @param request
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<QuestionVO>> listMyFavourQuestionByPage(@RequestBody QuestionQueryRequest questionQueryRequest,
                                                                     HttpServletRequest request) {
        if (questionQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        long current = questionQueryRequest.getCurrent();
        long size = questionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Question> postPage = questionFavourService.listFavourQuestionByPage(new Page<>(current, size),
                questionService.getQueryWrapper(questionQueryRequest), loginUser.getId());
        return ResultUtils.success(questionService.getQuestionVOPage(postPage, request));
    }

    /**
     * 获取用户收藏的题目列表
     *
     * @param questionFavourQueryRequest
     * @param request
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionVO>> listFavourQuestionByPage(@RequestBody QuestionFavourQueryRequest questionFavourQueryRequest,
                                                                   HttpServletRequest request) {
        if (questionFavourQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = questionFavourQueryRequest.getCurrent();
        long size = questionFavourQueryRequest.getPageSize();
        Long userId = questionFavourQueryRequest.getUserId();
        User user = userService.getLoginUser(request);
        // 限制爬虫
        ThrowUtils.throwIf(size > 20 || userId == null, ErrorCode.PARAMS_ERROR);
        Page<Question> questionPage = questionFavourService.listFavourQuestionByPage(new Page<>(current, size),
                questionService.getQueryWrapper(questionFavourQueryRequest.getQuestionQueryRequest()), userId);
        return ResultUtils.success(questionService.getQuestionVOPage(questionPage, request));
    }

    /**
     * 获取收藏状态
     **/
    @GetMapping("get/question_favour/status")
    public BaseResponse<Long> getQuestion_favourStatus(long questionId, HttpServletRequest request) {
        log.info("获取题目收藏状态：题目ID：{}, HTTP请求信息：{}", questionId, request);
        if (questionId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        Long userId = user.getId();
        // 从数据库中查询信息
        Long question_thumbStatus = questionFavourService.getQuestion_favourStatus(questionId, userId);
        return ResultUtils.success(question_thumbStatus);
    }
}
