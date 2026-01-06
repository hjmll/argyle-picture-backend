package com.argyle.argylepicturebackend.api.aliyunai.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片识别请求
 */
@Data
public class ImageRecognitionRequest implements Serializable {

    /**
     * 模型名称，使用通义千问视觉模型
     */
    private String model = "qwen-vl-plus";

    /**
     * 输入信息
     */
    private Input input;

    @Data
    public static class Input implements Serializable {
        /**
         * 图片URL
         */
        private String image;

        /**
         * 提示词，用于指导AI识别图片内容
         */
        private String prompt;
    }

    /**
     * 参数配置
     */
    private Parameters parameters;

    @Data
    public static class Parameters implements Serializable {
        /**
         * 温度参数，控制输出的随机性，范围0-2
         */
        private Double temperature = 0.7;

        /**
         * 最大输出token数
         */
        private Integer maxTokens = 2000;
    }
}


