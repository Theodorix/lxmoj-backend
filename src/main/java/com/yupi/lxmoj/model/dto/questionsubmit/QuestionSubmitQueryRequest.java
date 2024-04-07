package com.yupi.lxmoj.model.dto.questionsubmit;

import com.yupi.lxmoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//import java.io.Serial;

/**
 * 查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {


    /**
     * 题目状态
     */
    private String message;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 判题状态
     */
    private Integer status;


    /**
     * 消耗内存
     */
    private Integer memory;

    /**
     * 消耗时间
     */
    private Integer time;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 题目名称
     */
    private String title;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private  String userName;

    /**
     * 用户 id
     */
    private Long userId;


    /**
     * 搜索词
     */
    private String searchText;

    //@Serial
    private static final long serialVersionUID = 1L;
}