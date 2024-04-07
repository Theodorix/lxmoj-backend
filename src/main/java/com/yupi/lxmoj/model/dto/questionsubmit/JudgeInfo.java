package com.yupi.lxmoj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @ClassName JudgeInfo
 * @Description TODO
 * @Author LXY
 * @Date 2023/9/23 0:34
 * @Version 1.0
 */
@Data
public class JudgeInfo {


    /***
     * 程序执行信息
     */
    private String message;

    /***
     * 消耗时间
     */
    private Long time;

    /***
     * 消耗内存
     */
    private Long memory;
}
