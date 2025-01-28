package com.argyle.argylepicturebackend.controller;

import com.argyle.argylepicturebackend.annotation.AuthCheck;
import com.argyle.argylepicturebackend.common.BaseResponse;
import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.common.ResultUtils;
import com.argyle.argylepicturebackend.constant.UserConstant;
import com.argyle.argylepicturebackend.exception.BusinessException;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import com.argyle.argylepicturebackend.exception.ThrowUtils;
import com.argyle.argylepicturebackend.model.dto.tag.TagAddRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagQueryRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Tag;
import com.argyle.argylepicturebackend.model.vo.TagVO;
import com.argyle.argylepicturebackend.service.TagService;
import com.argyle.argylepicturebackend.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/01/28/10:08
 * @Description: 标签相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @Resource
    private UserService userService;

    /**
     * 新增标签
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addTag(@RequestBody TagAddRequest tagAddRequest, HttpServletRequest request) {
        if (tagAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        boolean result = tagService.addTag(tagAddRequest);
        return ResultUtils.success(result);
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteTag(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        long id = deleteRequest.getId();
        // 判断是否存在
        Tag oldTag = tagService.getById(id);
        ThrowUtils.throwIf(oldTag == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = tagService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新标签
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateTag(@RequestBody TagUpdateRequest tagUpdateRequest, HttpServletRequest request) {
        if (tagUpdateRequest == null || tagUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        // 将实体类和 DTO 进行转换
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagUpdateRequest, tag);
        // 数据校验
        tagService.validTag(tag);
        // 判断是否存在
        long id = tagUpdateRequest.getId();
        Tag oldTag = tagService.getById(id);
        ThrowUtils.throwIf(oldTag == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = tagService.updateById(tag);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取标签
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Tag> getTagById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        userService.getLoginUser(request); // 验证用户是否登录
        // 查询数据库
        Tag tag = tagService.getById(id);
        ThrowUtils.throwIf(tag == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(tag);
    }

    /**
     * 根据 id 获取标签（封装类）
     */
    @GetMapping("/get/vo")
    public BaseResponse<TagVO> getTagVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        userService.getLoginUser(request); // 验证用户是否登录
        // 查询数据库
        Tag tag = tagService.getById(id);
        ThrowUtils.throwIf(tag == null, ErrorCode.NOT_FOUND_ERROR);
        // 这里假设 TagService 有 getTagVO 方法来获取封装类
        return ResultUtils.success(tagService.getTagVO(tag, request));
    }

    /**
     * 分页获取标签列表
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<Tag>> listTagByPage(@RequestBody TagQueryRequest tagQueryRequest, HttpServletRequest request) {
        userService.getLoginUser(request); // 验证用户是否登录
        List<Tag> tagList = tagService.queryTag(tagQueryRequest);
        return ResultUtils.success(tagList);
    }

    /**
     * 分页获取标签列表（封装类）
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<TagVO>> listTagVOByPage(@RequestBody TagQueryRequest tagQueryRequest, HttpServletRequest request) {
        userService.getLoginUser(request); // 验证用户是否登录
        long current = tagQueryRequest.getCurrent();
        long pageSize = tagQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ErrorCode.PARAMS_ERROR);
        Page<Tag> tagPage = tagService.page(new Page<>(current, pageSize),
                tagService.getQueryWrapper(tagQueryRequest)
        );
        return ResultUtils.success(tagService.getTagVOPage(tagPage, request));
    }
}
