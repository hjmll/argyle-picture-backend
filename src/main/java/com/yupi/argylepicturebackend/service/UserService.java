package com.yupi.argylepicturebackend.service;

import com.yupi.argylepicturebackend.model.dto.UserRegisterRequest;
import com.yupi.argylepicturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hjm
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-01-23 22:36:20
*/
public interface UserService extends IService<User> {
    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 获取加密后的密码
     *
     * @param userPassword
     * @return
     */
    String getEncryptPassword(String userPassword);

}
