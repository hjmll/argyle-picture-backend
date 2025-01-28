package com.argyle.argylepicturebackend.controller;

import com.argyle.argylepicturebackend.annotation.AuthCheck;
import com.argyle.argylepicturebackend.common.BaseResponse;
import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.common.ResultUtils;
import com.argyle.argylepicturebackend.constant.UserConstant;
import com.argyle.argylepicturebackend.exception.BusinessException;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import com.argyle.argylepicturebackend.exception.ThrowUtils;
import com.argyle.argylepicturebackend.model.dto.category.CategoryAddRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryQueryRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.vo.CategoryVO;
import com.argyle.argylepicturebackend.service.CategoryService;
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
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    /**
     * 新增标签
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addCategory(@RequestBody CategoryAddRequest categoryAddRequest, HttpServletRequest request) {
        if (categoryAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        boolean result = categoryService.addCategory(categoryAddRequest);
        return ResultUtils.success(result);
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteCategory(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        long id = deleteRequest.getId();
        // 判断是否存在
        Category oldCategory = categoryService.getById(id);
        ThrowUtils.throwIf(oldCategory == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = categoryService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新标签
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest, HttpServletRequest request) {
        if (categoryUpdateRequest == null || categoryUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        userService.getLoginUser(request); // 验证用户是否登录
        // 将实体类和 DTO 进行转换
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateRequest, category);
        // 数据校验
        categoryService.validCategory(category);
        // 判断是否存在
        long id = categoryUpdateRequest.getId();
        Category oldCategory = categoryService.getById(id);
        ThrowUtils.throwIf(oldCategory == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = categoryService.updateById(category);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取标签
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Category> getCategoryById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        userService.getLoginUser(request); // 验证用户是否登录
        // 查询数据库
        Category category = categoryService.getById(id);
        ThrowUtils.throwIf(category == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(category);
    }

    /**
     * 根据 id 获取标签（封装类）
     */
    @GetMapping("/get/vo")
    public BaseResponse<CategoryVO> getCategoryVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        userService.getLoginUser(request); // 验证用户是否登录
        // 查询数据库
        Category category = categoryService.getById(id);
        ThrowUtils.throwIf(category == null, ErrorCode.NOT_FOUND_ERROR);
        // 这里假设 CategoryService 有 getCategoryVO 方法来获取封装类
        return ResultUtils.success(categoryService.getCategoryVO(category, request));
    }

    /**
     * 分页获取标签列表
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<Category>> listCategoryByPage(@RequestBody CategoryQueryRequest categoryQueryRequest, HttpServletRequest request) {
        userService.getLoginUser(request); // 验证用户是否登录
        List<Category> categoryList = categoryService.queryCategory(categoryQueryRequest);
        return ResultUtils.success(categoryList);
    }

    /**
     * 分页获取标签列表（封装类）
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<CategoryVO>> listCategoryVOByPage(@RequestBody CategoryQueryRequest categoryQueryRequest, HttpServletRequest request) {
        userService.getLoginUser(request); // 验证用户是否登录
        long current = categoryQueryRequest.getCurrent();
        long pageSize = categoryQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, ErrorCode.PARAMS_ERROR);
        Page<Category> categoryPage = categoryService.page(new Page<>(current, pageSize),
                categoryService.getQueryWrapper(categoryQueryRequest)
        );
        return ResultUtils.success(categoryService.getCategoryVOPage(categoryPage, request));
    }


}
