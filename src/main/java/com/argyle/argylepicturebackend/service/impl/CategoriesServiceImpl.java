package com.argyle.argylepicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.argyle.argylepicturebackend.model.entity.Categories;
import com.argyle.argylepicturebackend.service.CategoriesService;
import com.argyle.argylepicturebackend.mapper.CategoriesMapper;
import org.springframework.stereotype.Service;

/**
* @author hjm
* @description 针对表【categories(分类表)】的数据库操作Service实现
* @createDate 2025-01-28 00:07:51
*/
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
    implements CategoriesService{

}




