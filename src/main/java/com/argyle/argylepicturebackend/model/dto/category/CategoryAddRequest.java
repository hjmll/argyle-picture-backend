package com.argyle.argylepicturebackend.model.dto.category;

import com.argyle.argylepicturebackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类新增请求
 */
@Data
public class CategoryAddRequest implements Serializable {


    private String name;


    private static final long serialVersionUID = 1L;
}
