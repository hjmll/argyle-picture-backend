package com.argyle.argylepicturebackend.model.dto.category;

import com.argyle.argylepicturebackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类更新请求
 */
@Data
public class CategoryQueryRequest extends PageRequest implements Serializable {

    private Long id;


    private String name;


    private static final long serialVersionUID = 1L;
}
