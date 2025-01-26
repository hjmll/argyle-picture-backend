package com.yupi.argylepicturebackend.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/01/26/21:09
 * @Description: 图片标签分类列表视图
 */
@Data
public class PictureTagCategory {
    /**
     * 标签列表
     */
    private List<String> tagList;
    /**
     * 分类列表
     */
    private List<String> categoryList;

}
