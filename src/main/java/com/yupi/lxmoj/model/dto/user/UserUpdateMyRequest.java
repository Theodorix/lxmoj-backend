package com.yupi.lxmoj.model.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户更新个人信息请求
 */
@Data
public class UserUpdateMyRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * GigHub
     */
    private String gitHubName;


    /**
     * 个人网站、博客或者作品集等
     */
    private List<website> websites;


    /**
     * 性别 0为女性 1为男性
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 就读学校
     */
    private String school;

    /**
     * 公司
     */
    private String company;

    /**
     * 职位
     */
    private String position;


    private static final long serialVersionUID = 1L;
}