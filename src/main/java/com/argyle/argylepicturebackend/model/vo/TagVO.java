package com.argyle.argylepicturebackend.model.vo;


import org.springframework.beans.BeanUtils;
import com.argyle.argylepicturebackend.model.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TagVO implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String name;

    /**
     * 标签使用次数
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

    public static Tag voToObj(TagVO tagVO) {
        if (tagVO == null) {
            return null;
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagVO, tag);
        return tag;
    }
    public static TagVO objToVo(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagVO tagVO = new TagVO();
        BeanUtils.copyProperties(tag, tagVO);
        return tagVO;
    }
}
