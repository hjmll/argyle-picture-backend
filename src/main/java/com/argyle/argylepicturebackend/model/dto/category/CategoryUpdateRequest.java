package com.argyle.argylepicturebackend.model.dto.category;

import com.argyle.argylepicturebackend.common.PageRequest;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类更新请求
 */
@Data
public class CategoryUpdateRequest implements Serializable {

    private Long id;


    private String name;


    private static final long serialVersionUID = 1L;
}
