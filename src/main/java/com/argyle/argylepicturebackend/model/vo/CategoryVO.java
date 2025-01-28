package com.argyle.argylepicturebackend.model.vo;

import com.argyle.argylepicturebackend.model.entity.Category;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: hjm
 * @Date: 2025/01/28/15:49
 * @Description:
 */
public class CategoryVO implements Serializable {

    private Long id;

    /**
     *
     */
    private String name;

    /**
     * 分类使用次数
     */
    private Integer usageCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    private static final long serialVersionUID = 1L;

    public static Category voToObj(CategoryVO CategoryVO) {
        if (CategoryVO == null) {
            return null;
        }
        Category Category = new Category();
        BeanUtils.copyProperties(CategoryVO, Category);
        return Category;
    }
    public static CategoryVO objToVo(Category Category) {
        if (Category == null) {
            return null;
        }
        CategoryVO CategoryVO = new CategoryVO();
        BeanUtils.copyProperties(Category, CategoryVO);
        return CategoryVO;
    }
}
