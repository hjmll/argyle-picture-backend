package com.argyle.argylepicturebackend.model.dto.tag;

import com.argyle.argylepicturebackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 标签更新请求
 */
@Data
public class TagQueryRequest extends PageRequest implements Serializable {
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
