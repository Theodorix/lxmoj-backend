package com.yupi.lxmoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.lxmoj.model.dto.questionsubmit.QuestionRunAddRequest;
import com.yupi.lxmoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.lxmoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.lxmoj.model.entity.QuestionSubmit;
import com.yupi.lxmoj.model.entity.User;
import com.yupi.lxmoj.model.vo.QuestionSubmitConsumptionTimeAndMemoryRankingResponse;
import com.yupi.lxmoj.model.vo.QuestionSubmitRunResultResponse;
import com.yupi.lxmoj.model.vo.QuestionSubmitStatusResponse;
import com.yupi.lxmoj.model.vo.QuestionSubmitVO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 题目运行
     *
     * @param questionRunAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    QuestionSubmitRunResultResponse doQuestionRun(QuestionRunAddRequest questionRunAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


//    Boolean updateQuestionSubmit(QuestionSubmitUpdateRequest questionSubmitUpdateRequest);

    long getQuestionSubmitStatus(long questionId, long userId);

    QuestionSubmitStatusResponse getQuestionSubmitStatusByUser(User loginUser);


    long getQuestionSubmitDifficulty(long difficulty, Long userId);

    double getQuestionSubmitDifficultyPassRate(long difficulty, Long userId);

    double getQuestionSubmitPassRate(Long userId);


    QuestionSubmitConsumptionTimeAndMemoryRankingResponse getQuestionSubmitConsumptionTimeAndMemoryRanking(long questionId, long questionSubmitId, String language, HttpServletRequest request);

}
