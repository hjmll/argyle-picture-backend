package com.argyle.argylepicturebackend.controller;

import com.argyle.argylepicturebackend.common.BaseResponse;
import com.argyle.argylepicturebackend.common.ResultUtils;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import com.argyle.argylepicturebackend.exception.ThrowUtils;
import com.argyle.argylepicturebackend.model.dto.space.analyze.SpaceCategoryAnalyzeRequest;
import com.argyle.argylepicturebackend.model.dto.space.analyze.SpaceUsageAnalyzeRequest;
import com.argyle.argylepicturebackend.model.entity.User;
import com.argyle.argylepicturebackend.model.vo.space.analyze.SpaceCategoryAnalyzeResponse;
import com.argyle.argylepicturebackend.model.vo.space.analyze.SpaceUsageAnalyzeResponse;
import com.argyle.argylepicturebackend.service.SpaceAnalyzeService;
import com.argyle.argylepicturebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/space/analyze")
public class SpaceAnalyzeController {

    @Resource
    private SpaceAnalyzeService spaceAnalyzeService;

    @Resource
    private UserService userService;

    /**
     * 获取空间使用状态
     */
    @PostMapping("/usage")
    public BaseResponse<SpaceUsageAnalyzeResponse> getSpaceUsageAnalyze(
            @RequestBody SpaceUsageAnalyzeRequest spaceUsageAnalyzeRequest,
            HttpServletRequest request
    ) {
        ThrowUtils.throwIf(spaceUsageAnalyzeRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        SpaceUsageAnalyzeResponse spaceUsageAnalyze = spaceAnalyzeService.getSpaceUsageAnalyze(spaceUsageAnalyzeRequest, loginUser);
        return ResultUtils.success(spaceUsageAnalyze);
    }

    @PostMapping("/category")
    public BaseResponse<List<SpaceCategoryAnalyzeResponse>> getSpaceCategoryAnalyze(@RequestBody SpaceCategoryAnalyzeRequest spaceCategoryAnalyzeRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(spaceCategoryAnalyzeRequest == null, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        List<SpaceCategoryAnalyzeResponse> resultList = spaceAnalyzeService.getSpaceCategoryAnalyze(spaceCategoryAnalyzeRequest, loginUser);
        return ResultUtils.success(resultList);
    }

}
