package com.argyle.argylepicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量导入图片的请求
 */

@Data
public class PictureUploadByBatchRequest implements Serializable {

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 抓取数量
     */
    private Integer count = 10;
    /**
     * 图片名称前缀
     */
    private String namePrefix;
    /**
     * 偏移量
     */
    private Integer offset;

    private String category; // 新增：图片分类
    private List<String> tags; // 新增：图片标签列表
    private static final long serialVersionUID = 1L;
}
