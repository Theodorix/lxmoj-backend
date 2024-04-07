package com.yupi.lxmoj.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

//import java.io.Serial;

/**
 * 创建请求
 */
@Data
public class QuestionRunAddRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 输入用例
     */
    private List<String[]> inputList;


//    //@Serial
    private static final long serialVersionUID = 1L;
}