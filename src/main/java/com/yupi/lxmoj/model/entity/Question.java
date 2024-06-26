package com.yupi.lxmoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

////import java.io.Serial;

/**
 * 题目
 *
 * @TableName question
 */
@TableName(value = "question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 前端代码
     */
    private String frontendCode;

    /**
     * 后端代码
     */
    private String backendCode;

    /**
     * 逻辑代码
     */
    private String logicCode;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 难度 1-简单 2-中等 3-困难
     */
    private Integer difficulty;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

//    /**
//     * 通过率
//     */
//    private BigDecimal passRate;

    /**
     * 判题用例 (json 数组)
     */
    private String judgeCase;

    /**
     * 判题配置 (json 数组)
     */
    private String judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

//    //@Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}