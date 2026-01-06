package com.argyle.argylepicturebackend.api.aliyunai.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片识别响应
 */
@Data
public class ImageRecognitionResponse implements Serializable {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 输出结果
     */
    private Output output;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    @Data
    public static class Output implements Serializable {
        /**
         * 识别结果文本
         */
        private String text;

        /**
         * 完成原因
         */
        private String finishReason;
    }
}


