package com.argyle.argylepicturebackend.model.dto.tag;

import lombok.Data;

import java.io.Serializable;

/**
 * 标签新增请求
 */
@Data
public class TagAddRequest implements Serializable {

    private String name;

    private static final long serialVersionUID = 1L;
}
