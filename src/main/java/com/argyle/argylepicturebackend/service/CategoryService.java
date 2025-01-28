package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryAddRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryQueryRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryUpdateRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryAddRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryQueryRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.vo.CategoryVO;
import com.argyle.argylepicturebackend.model.vo.CategoryVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hjm
* @description 针对表【categories(分类表)】的数据库操作Service
* @createDate 2025-01-28 00:07:51
*/
public interface CategoryService extends IService<Category> {
    void validCategory(Category category);

    boolean addCategory(CategoryAddRequest categorysAddRequest);

    boolean updateCategory(CategoryUpdateRequest categorysUpdateRequest);

    boolean deleteCategory(DeleteRequest deleteRequest);

    List<Category> queryCategory(CategoryQueryRequest categoryQueryRequest);


    CategoryVO getCategoryVO(Category category, HttpServletRequest request);

    Page<CategoryVO> getCategoryVOPage(Page<Category> categoryPage, HttpServletRequest request);

    QueryWrapper<Category> getQueryWrapper(CategoryQueryRequest categoryQueryRequest);
}
