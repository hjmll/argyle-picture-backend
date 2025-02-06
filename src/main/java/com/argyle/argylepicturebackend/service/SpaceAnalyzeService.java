package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.model.dto.space.analyze.SpaceCategoryAnalyzeRequest;
import com.argyle.argylepicturebackend.model.dto.space.analyze.SpaceTagAnalyzeRequest;
import com.argyle.argylepicturebackend.model.dto.space.analyze.SpaceUsageAnalyzeRequest;
import com.argyle.argylepicturebackend.model.entity.Space;
import com.argyle.argylepicturebackend.model.entity.User;
import com.argyle.argylepicturebackend.model.vo.space.analyze.SpaceCategoryAnalyzeResponse;
import com.argyle.argylepicturebackend.model.vo.space.analyze.SpaceTagAnalyzeResponse;
import com.argyle.argylepicturebackend.model.vo.space.analyze.SpaceUsageAnalyzeResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/02/05/19:53
 * @Description:
 */
public interface SpaceAnalyzeService extends IService<Space> {

    SpaceUsageAnalyzeResponse getSpaceUsageAnalyze(SpaceUsageAnalyzeRequest spaceUsageAnalyzeRequest, User loginUser);

    List<SpaceCategoryAnalyzeResponse> getSpaceCategoryAnalyze(SpaceCategoryAnalyzeRequest spaceCategoryAnalyzeRequest, User loginUser);

    List<SpaceTagAnalyzeResponse> getSpaceTagAnalyze(SpaceTagAnalyzeRequest spaceTagAnalyzeRequest, User loginUser);
}
