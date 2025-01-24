package com.yupi.argylepicturebackend.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hjm
 * @Date: 2025/01/23/23:19
 * @Description: 用户注册请求
 */

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;


}
