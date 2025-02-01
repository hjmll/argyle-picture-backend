package com.argyle.argylepicturebackend.service.impl;

import com.argyle.argylepicturebackend.mapper.CategoryMapper;
import com.argyle.argylepicturebackend.mapper.PictureMapper;
import com.argyle.argylepicturebackend.mapper.TagMapper;
import com.argyle.argylepicturebackend.model.entity.Category;
import com.argyle.argylepicturebackend.model.entity.Tag;
import com.argyle.argylepicturebackend.service.StatisticsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private PictureMapper pictureMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private CategoryMapper categoryMapper;


    // 更新标签使用次数
    @Override
    public void updateTagUsageCount(List<String> tagNames) {
        if (tagNames != null && !tagNames.isEmpty()) {
            for (String tagName : tagNames) {
                QueryWrapper<Tag> wrapper = new QueryWrapper<>();
                wrapper.eq("name", tagName);
                Tag tag = tagMapper.selectOne(wrapper);
                if (tag != null) {
                    tag.setUsageCount(tag.getUsageCount() + 1);
                    tagMapper.updateById(tag);
                }
            }
        }
    }

    // 更新分类使用次数
    @Override
    public void updateCategoryUsageCount(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            QueryWrapper<Category> wrapper = new QueryWrapper<>();
            wrapper.eq("name", categoryName);
            Category category = categoryMapper.selectOne(wrapper);
            if (category != null) {
                category.setUsageCount(category.getUsageCount() + 1);
                categoryMapper.updateById(category);
            }
        }
    }

    // 统计热门标签
    @Override
    public List<Map.Entry<String, Integer>> getPopularTags(int topN) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("usageCount");
        wrapper.last("limit " + topN);
        List<Tag> tagList = tagMapper.selectList(wrapper);

        return tagList.stream()
               .collect(Collectors.toMap(Tag::getName, Tag::getUsageCount))
               .entrySet().stream()
               .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
               .collect(Collectors.toList());
    }

    // 统计热门分类
    @Override
    public List<Map.Entry<String, Integer>> getPopularCategories(int topN) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("usageCount");
        wrapper.last("limit " + topN);
        List<Category> categoryList = categoryMapper.selectList(wrapper);

        return categoryList.stream()
               .collect(Collectors.toMap(Category::getName, Category::getUsageCount))
               .entrySet().stream()
               .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
               .collect(Collectors.toList());
    }
}
