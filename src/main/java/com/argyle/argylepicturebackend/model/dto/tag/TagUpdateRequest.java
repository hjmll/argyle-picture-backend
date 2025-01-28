package com.argyle.argylepicturebackend.model.dto.tag;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签更新请求
 */
@Data
public class TagUpdateRequest implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
