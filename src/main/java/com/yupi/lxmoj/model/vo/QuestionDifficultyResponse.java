package com.yupi.lxmoj.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目提交封装类
 *
 * @TableName question
 */
@Data
public class QuestionDifficultyResponse implements Serializable {


    /***
     * 简单题目数量
     */
    private Long simpleQuestionNum;

    /***
     * 中等题目数量
     */
    private Long mediumQuestionNum;

    /***
     * 困难题目数量
     */
    private Long difficultQuestionNum;


    private static final long serialVersionUID = 1L;
}