package com.argyle.argylepicturebackend.controller;

import com.argyle.argylepicturebackend.common.BaseResponse;
import com.argyle.argylepicturebackend.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjm
 * @Date: 2025/01/21/11:43
 * @Description:
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/health")
    public BaseResponse<String> healthCheck() {
        return ResultUtils.success("ok");
    }
}
