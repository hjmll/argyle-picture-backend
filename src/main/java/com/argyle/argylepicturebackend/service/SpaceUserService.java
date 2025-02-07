package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.model.dto.spaceuser.SpaceUserAddRequest;
import com.argyle.argylepicturebackend.model.dto.spaceuser.SpaceUserQueryRequest;
import com.argyle.argylepicturebackend.model.entity.SpaceUser;
import com.argyle.argylepicturebackend.model.vo.SpaceUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hjm
* @description 针对表【space_user(空间用户关联)】的数据库操作Service
* @createDate 2025-02-07 18:35:35
*/
public interface SpaceUserService extends IService<SpaceUser> {

    long addSpaceUser(SpaceUserAddRequest spaceUserAddRequest);

    void validSpaceUser(SpaceUser spaceUser, boolean add);

    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);

    SpaceUserVO getSpaceUserVO(SpaceUser spaceUser, HttpServletRequest request);

    List<SpaceUserVO> getSpaceUserVOList(List<SpaceUser> spaceUserList);
}
