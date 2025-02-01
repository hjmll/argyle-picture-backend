package com.argyle.argylepicturebackend.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: hjm
 * @Date: 2025/02/01/20:07
 * @Description:
 */
public interface StatisticsService {
    // 更新标签使用次数
    void updateTagUsageCount(List<String> tagNames);

    // 更新分类使用次数
    void updateCategoryUsageCount(String categoryName);

    // 统计热门标签
    List<Map.Entry<String, Integer>> getPopularTags(int topN);

    // 统计热门分类
    List<Map.Entry<String, Integer>> getPopularCategories(int topN);
}
