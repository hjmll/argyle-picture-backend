package com.argyle.argylepicturebackend.utils;

import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * AI标签提取工具类
 * 从AI描述中提取关键词作为标签
 */
public class AiTagExtractor {

    // 常见停用词（不需要作为标签的词）
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "的", "了", "在", "是", "有", "和", "与", "或", "但", "这", "那", "一个", "一些", "很多", "非常",
            "很", "非常", "特别", "十分", "比较", "更加", "可以", "能够", "应该", "可能", "也许",
            "图片", "图像", "照片", "画面", "场景", "内容", "元素", "对象", "物品", "东西"
    ));

    // 常见标点符号
    private static final Pattern PUNCTUATION = Pattern.compile("[，。、；：！？''（）【】《》\\s]+");

    /**
     * 从AI描述中提取关键词作为标签
     *
     * @param description AI描述文本
     * @return 标签列表（最多返回20个）
     */
    public static List<String> extractTags(String description) {
        if (StrUtil.isBlank(description)) {
            return Collections.emptyList();
        }

        // 移除标点符号，按标点分割
        String[] sentences = PUNCTUATION.split(description);
        Set<String> tagSet = new HashSet<>();

        for (String sentence : sentences) {
            if (StrUtil.isBlank(sentence)) {
                continue;
            }
            // 提取2-6字的关键词
            extractKeywords(sentence, tagSet);
        }

        // 过滤停用词，按长度和频率排序，取前20个
        return tagSet.stream()
                .filter(tag -> tag.length() >= 2 && tag.length() <= 6)
                .filter(tag -> !STOP_WORDS.contains(tag))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(20)
                .collect(Collectors.toList());
    }

    /**
     * 从句子中提取关键词
     */
    private static void extractKeywords(String sentence, Set<String> tagSet) {
        int len = sentence.length();
        // 提取2-6字的词组
        for (int i = 0; i < len; i++) {
            for (int j = 2; j <= 6 && i + j <= len; j++) {
                String word = sentence.substring(i, i + j);
                // 过滤掉纯数字和纯英文
                if (!isPureNumberOrEnglish(word)) {
                    tagSet.add(word);
                }
            }
        }
    }

    /**
     * 判断是否为纯数字或纯英文
     */
    private static boolean isPureNumberOrEnglish(String word) {
        return word.matches("^[0-9]+$") || word.matches("^[a-zA-Z]+$");
    }
}


