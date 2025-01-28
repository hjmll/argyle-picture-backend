package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagAddRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagQueryRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Tag;
import com.argyle.argylepicturebackend.model.vo.TagVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hjm
* @description 针对表【tag(标签表)】的数据库操作Service
* @createDate 2025-01-28 00:07:58
*/
public interface TagService extends IService<Tag> {
    void validTag(Tag tag);

    boolean addTag(TagAddRequest tagsAddRequest);

    boolean updateTag(TagUpdateRequest tagsUpdateRequest);

    boolean deleteTag(DeleteRequest deleteRequest);

    List<Tag> queryTag(TagQueryRequest tagQueryRequest);


    TagVO getTagVO(Tag tag, HttpServletRequest request);

    Page<TagVO> getTagVOPage(Page<Tag> tagPage, HttpServletRequest request);

    QueryWrapper<Tag> getQueryWrapper(TagQueryRequest tagQueryRequest);
}
