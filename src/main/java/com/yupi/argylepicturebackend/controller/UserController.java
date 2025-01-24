package com.yupi.argylepicturebackend.controller;

import com.yupi.argylepicturebackend.annotation.AuthCheck;
import com.yupi.argylepicturebackend.common.BaseResponse;
import com.yupi.argylepicturebackend.common.ResultUtils;
import com.yupi.argylepicturebackend.constant.UserConstant;
import com.yupi.argylepicturebackend.exception.ErrorCode;
import com.yupi.argylepicturebackend.exception.ThrowUtils;
import com.yupi.argylepicturebackend.model.dto.UserLoginRequest;
import com.yupi.argylepicturebackend.model.dto.UserRegisterRequest;
import com.yupi.argylepicturebackend.model.entity.User;
import com.yupi.argylepicturebackend.model.vo.LoginUserVO;
import com.yupi.argylepicturebackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: hjm
 * @Date: 2025/01/21/11:43
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userResgister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        long result = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.OPERATION_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

}
