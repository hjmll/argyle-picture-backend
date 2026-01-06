package com.argyle.argylepicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片AI识别请求
 */
@Data
public class PictureRecognitionRequest implements Serializable {

    /**
     * 图片ID
     */
    private Long pictureId;

    private static final long serialVersionUID = 1L;
}


