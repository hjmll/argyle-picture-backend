package com.yupi.argylepicturebackend.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hjm
 * @Date: 2025/01/24/11:24
 * @Description: 用户登录请求
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
