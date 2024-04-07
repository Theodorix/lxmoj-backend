package com.yupi.lxmoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.lxmoj.model.dto.question.QuestionQueryRequest;
import com.yupi.lxmoj.model.entity.Question;
import com.yupi.lxmoj.model.vo.QuestionDifficultyResponse;
import com.yupi.lxmoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface QuestionService extends IService<Question> {

    /**
     * 校验
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);


    /**
     * 获取题目封装
     *
     * @param question
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);


    /**
     * 分页获取帖子封装
     *
     * @param questionPage
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);


    QuestionDifficultyResponse getQuestionDifficulty(HttpServletRequest request);

    Page<QuestionVO> listQuestionVOByPage(QuestionQueryRequest questionQueryRequest, HttpServletRequest request);
}
