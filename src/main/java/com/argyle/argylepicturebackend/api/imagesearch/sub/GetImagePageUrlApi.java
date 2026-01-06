package com.argyle.argylepicturebackend.api.imagesearch.sub;


import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.argyle.argylepicturebackend.exception.BusinessException;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GetImagePageUrlApi {

    /**
     * 获取图片页面地址
     *
     * @param imageUrl
     * @return
     */
    public static String getImagePageUrl(String imageUrl) {
        // 1. 准备请求参数
        Map<String, Object> formData = new HashMap<>();
        formData.put("image", imageUrl);
        formData.put("tn", "pc");
        formData.put("from", "pc");
        formData.put("image_source", "PC_UPLOAD_URL");
        // 获取当前时间戳
        long uptime = System.currentTimeMillis();
        // 请求地址
        String url = "https://graph.baidu.com/upload?uptime=" + uptime;
        String acsToken = "1749615995919_1749653813682_PewzWlz7XY1vYKEVJIhm3gYOpaGgmPVeutFQEEpWgcbFp/TxqMu6b0xV8yIbLpOAVMEvALhleQIXu/GK0vtdx3dpMt3PVc4b7eq52LvjowS6ZRdl9mQeaW7XYqL9MKNSlvZzbbSa8bi9i28J5u4RHqYUl6ZJLip24l0xm6MnKSzv7AwR8QewFR7oTzoFBWqyFX8dRycmBRkkb0//IQVwRFkOu8s00JPLiqog2o6aUNddW+lsdzXiIx0vkwMtsBH/0K6SoDnXE9wbDd3VhKnVkubpKfci9aLoyTiqd8pK9pc2PcPUKUVloGOQfdNmzX0rW9AJ6ImoAyn9HafgtgpEJbbISXfey7si6BrvTlRiEBEBOOsHYfGmyxgm1rh3C9G3+Et2U+VNmCF2RqJ7EkrQIv5oe8kzQZoa5JMsnU1aiDhAAMawWm55NiOagflKECy3";

        try {
            // 2. 发送 POST 请求到百度接口
            HttpResponse response = HttpRequest.post(url)
                    .form(formData)
                    .header("Acs-Token",acsToken)
                    .timeout(5000)
                    .execute();
            // 判断响应状态
            if (HttpStatus.HTTP_OK != response.getStatus()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
            }
            // 解析响应
            String responseBody = response.body();
            Map<String, Object> result = JSONUtil.toBean(responseBody, Map.class);

            // 3. 处理响应结果
            if (result == null || !Integer.valueOf(0).equals(result.get("status"))) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
            }
            Map<String, Object> data = (Map<String, Object>) result.get("data");
            String rawUrl = (String) data.get("url");
            // 对 URL 进行解码
            String searchResultUrl = URLUtil.decode(rawUrl, StandardCharsets.UTF_8);
            // 如果 URL 为空
            if (searchResultUrl == null) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "未返回有效结果");
            }
            return searchResultUrl;
        } catch (Exception e) {
            log.error("搜索失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "搜索失败");
        }
    }

    public static void main(String[] args) {
        // 测试以图搜图功能
        String imageUrl = "https://www.codefather.cn/logo.png";
        String result = getImagePageUrl(imageUrl);
        System.out.println("搜索成功，结果 URL：" + result);
    }
}
