package com.argyle.argylepicturebackend.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import com.argyle.argylepicturebackend.exception.ThrowUtils;
import com.argyle.argylepicturebackend.mapper.CategoryMapper;
import com.argyle.argylepicturebackend.model.dto.category.CategoryAddRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryQueryRequest;
import com.argyle.argylepicturebackend.model.dto.category.CategoryUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.vo.CategoryVO;
import com.argyle.argylepicturebackend.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
* @author hjm
* @description 针对表【categories(分类表)】的数据库操作Service实现
* @createDate 2025-01-28 00:07:51
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Override
    public void validCategory(Category categorys) {
        ThrowUtils.throwIf(categorys == null, ErrorCode.PARAMS_ERROR);
        Long id = categorys.getId();
        String name = categorys.getName();
        // 修改数据时，id 不能为空
        ThrowUtils.throwIf(ObjUtil.isNull(id), ErrorCode.PARAMS_ERROR, "id 不能为空");
        if (StrUtil.isNotBlank(name)) {
            // 可根据实际情况添加对名称长度等的校验
            ThrowUtils.throwIf(name.length() > 25, ErrorCode.PARAMS_ERROR, "标签名称不超过25个字符");
        }
    }

    @Override
    public boolean addCategory(CategoryAddRequest categorysAddRequest) {
        ThrowUtils.throwIf(categorysAddRequest == null || StrUtil.isBlank(categorysAddRequest.getName()), ErrorCode.PARAMS_ERROR, "标签名称不能为空");
        Category category = new Category();
        category.setName(categorysAddRequest.getName());
        category.setUsageCount(0);

        category.setEditTime(new Date());

        return this.save(category);
    }

    @Override
    public boolean updateCategory(CategoryUpdateRequest categorysUpdateRequest) {
        ThrowUtils.throwIf(categorysUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = categorysUpdateRequest.getId();
        // 校验标签是否存在
        ThrowUtils.throwIf(ObjUtil.isNull(id), ErrorCode.PARAMS_ERROR, "标签 id 不能为空");
        Category oldCategory = this.getById(id);
        ThrowUtils.throwIf(oldCategory == null, ErrorCode.NOT_FOUND_ERROR, "标签不存在");
        Category newCategory = new Category();
        newCategory.setId(id);
        if (StrUtil.isNotBlank(categorysUpdateRequest.getName())) {
            newCategory.setName(categorysUpdateRequest.getName());
        }
        newCategory.setEditTime(new Date());
        newCategory.setUpdateTime(new Date());
        this.validCategory(newCategory);
        return this.updateById(newCategory);
    }

    @Override
    public boolean deleteCategory(DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || ObjUtil.isNull(deleteRequest.getId()), ErrorCode.PARAMS_ERROR, "标签 id 不能为空");
        Long id = deleteRequest.getId();
        // 校验标签是否存在
        Category category = this.getById(id);
        ThrowUtils.throwIf(category == null, ErrorCode.NOT_FOUND_ERROR, "标签不存在");
        return this.removeById(id);
    }

    @Override
    public List<Category> queryCategory(CategoryQueryRequest categoryQueryRequest) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (categoryQueryRequest != null) {
            Long id = categoryQueryRequest.getId();
            String name = categoryQueryRequest.getName();
            queryWrapper.eq(ObjUtil.isNotEmpty(id), "id", id);
            queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        }
        return this.list(queryWrapper);
    }

    @Override
    public CategoryVO getCategoryVO(Category category, HttpServletRequest request) {
        return CategoryVO.objToVo(category);
    }

    @Override
    public Page<CategoryVO> getCategoryVOPage(Page<Category> categoryPage, HttpServletRequest request) {
        List<Category> categoryList = categoryPage.getRecords();
        Page<CategoryVO> categoryVOPage = new Page<>(categoryPage.getCurrent(), categoryPage.getSize(), categoryPage.getTotal());
        if (CollUtil.isEmpty(categoryList)){
            return categoryVOPage;
        }
        // 对象列表 => 封装对象列表
        List<CategoryVO> categoryVOList = categoryList.stream().map(CategoryVO::objToVo).collect(Collectors.toList());
        categoryVOList.forEach(categoryVO -> {
            categoryVOPage.setRecords(categoryVOList);
        });
        return categoryVOPage;
    }

    @Override
    public QueryWrapper<Category> getQueryWrapper(CategoryQueryRequest categoryQueryRequest) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (categoryQueryRequest == null) {
            return queryWrapper;
        }
        Long id = categoryQueryRequest.getId();
        String name = categoryQueryRequest.getName();
        String sortField = categoryQueryRequest.getSortField();
        String sortOrder = categoryQueryRequest.getSortOrder();
        queryWrapper.eq(ObjUtil.isNotEmpty(id),"id", categoryQueryRequest.getId());
        queryWrapper.like(StrUtil.isNotBlank(name), "name", categoryQueryRequest.getName());
        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }
}




