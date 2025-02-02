package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.model.dto.space.SpaceAddRequest;
import com.argyle.argylepicturebackend.model.dto.space.SpaceQueryRequest;
import com.argyle.argylepicturebackend.model.entity.Space;
import com.argyle.argylepicturebackend.model.entity.User;
import com.argyle.argylepicturebackend.model.vo.SpaceVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
* @author hjm
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-02-01 23:06:22
*/
public interface SpaceService extends IService<Space> {


    void validSpace(Space space, boolean add);

    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);

    SpaceVO getSpaceVO(Space space, HttpServletRequest request);



    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage, HttpServletRequest request);

    QueryWrapper<Space> getQueryWrapper(SpaceQueryRequest spaceQueryRequest);


    void fillSpaceBySpaceLevel(Space space);


    @Transactional(rollbackFor = Exception.class)
    boolean deleteSpace(Long spaceId);


    @Transactional(propagation = Propagation.MANDATORY)
    void adjustSpaceUsage(Long spaceId, long sizeDelta, int countDelta);
}
