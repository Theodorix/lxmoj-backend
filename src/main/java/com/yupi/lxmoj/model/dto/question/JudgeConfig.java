package com.yupi.lxmoj.model.dto.question;

import lombok.Data;

import java.io.Serializable;


/**
 * 判题配置
 *
 * @ClassName JudgeCase
 * @Description TODO
 * @Author lxy23
 * @Date 2023/9/23 0:34
 * @Version 1.0
 */
@Data
public class JudgeConfig implements Serializable {

    /**
     * 时间限制（ms）
     */
    private Long timeLimit;

    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;

    //@Serial
    private static final long serialVersionUID = 1L;
}
