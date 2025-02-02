package com.argyle.argylepicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PictureUploadRequest implements Serializable {

    /**
     * 图片 id（用于修改）
     */
    private Long id;
    /**
     * 图片 url
     */
    private String fileUrl;

    /**
     * 空间 id（为空表示公共空间）
     */
    private Long spaceId;

    /**
     * 图片名称
     */
    private String picName;
    private String category; // 新增：图片分类
    private List<String> tags; // 新增：图片标签列表
    private static final long serialVersionUID = 1L;
}
