package com.yupi.lxmoj.model.dto.question;

import com.yupi.lxmoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 包含ids
     */
    private List<Long> containIds;


    /**
     * 不包含ids
     */
    private List<Long> notContainIds;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;


    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 难度 1-简单 2-中等 3-困难
     */
    private Integer difficulty;


    /***
     * 通过率
     */
    private double passRate;


    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}