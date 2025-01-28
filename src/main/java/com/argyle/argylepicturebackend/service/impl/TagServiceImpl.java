package com.argyle.argylepicturebackend.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.argyle.argylepicturebackend.common.DeleteRequest;
import com.argyle.argylepicturebackend.exception.ErrorCode;
import com.argyle.argylepicturebackend.exception.ThrowUtils;
import com.argyle.argylepicturebackend.mapper.TagMapper;
import com.argyle.argylepicturebackend.model.dto.tag.TagAddRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagQueryRequest;
import com.argyle.argylepicturebackend.model.dto.tag.TagUpdateRequest;
import com.argyle.argylepicturebackend.model.entity.Tag;
import com.argyle.argylepicturebackend.model.vo.TagVO;
import com.argyle.argylepicturebackend.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author hjm
* @description 针对表【tags(标签表)】的数据库操作Service实现
* @createDate 2025-01-28 00:07:58
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    @Override
    public void validTag(Tag tags) {
        ThrowUtils.throwIf(tags == null, ErrorCode.PARAMS_ERROR);
        Long id = tags.getId();
        String name = tags.getName();
        // 修改数据时，id 不能为空
        ThrowUtils.throwIf(ObjUtil.isNull(id), ErrorCode.PARAMS_ERROR, "id 不能为空");
        if (StrUtil.isNotBlank(name)) {
            // 可根据实际情况添加对名称长度等的校验
            ThrowUtils.throwIf(name.length() > 25, ErrorCode.PARAMS_ERROR, "标签名称不超过25个字符");
        }
    }

    @Override
    public boolean addTag(TagAddRequest tagsAddRequest) {
        ThrowUtils.throwIf(tagsAddRequest == null || StrUtil.isBlank(tagsAddRequest.getName()), ErrorCode.PARAMS_ERROR, "标签名称不能为空");
        Tag tag = new Tag();
        tag.setName(tagsAddRequest.getName());
        tag.setUsageCount(0);

        tag.setEditTime(new Date());

        return this.save(tag);
    }

    @Override
    public boolean updateTag(TagUpdateRequest tagsUpdateRequest) {
        ThrowUtils.throwIf(tagsUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = tagsUpdateRequest.getId();
        // 校验标签是否存在
        ThrowUtils.throwIf(ObjUtil.isNull(id), ErrorCode.PARAMS_ERROR, "标签 id 不能为空");
        Tag oldTag = this.getById(id);
        ThrowUtils.throwIf(oldTag == null, ErrorCode.NOT_FOUND_ERROR, "标签不存在");
        Tag newTag = new Tag();
        newTag.setId(id);
        if (StrUtil.isNotBlank(tagsUpdateRequest.getName())) {
            newTag.setName(tagsUpdateRequest.getName());
        }
        newTag.setEditTime(new Date());
        newTag.setUpdateTime(new Date());
        this.validTag(newTag);
        return this.updateById(newTag);
    }

    @Override
    public boolean deleteTag(DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || ObjUtil.isNull(deleteRequest.getId()), ErrorCode.PARAMS_ERROR, "标签 id 不能为空");
        Long id = deleteRequest.getId();
        // 校验标签是否存在
        Tag tag = this.getById(id);
        ThrowUtils.throwIf(tag == null, ErrorCode.NOT_FOUND_ERROR, "标签不存在");
        return this.removeById(id);
    }

    @Override
    public List<Tag> queryTag(TagQueryRequest tagQueryRequest) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        if (tagQueryRequest != null) {
            Long id = tagQueryRequest.getId();
            String name = tagQueryRequest.getName();
            queryWrapper.eq(ObjUtil.isNotEmpty(id), "id", id);
            queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        }
        return this.list(queryWrapper);
    }

    @Override
    public TagVO getTagVO(Tag tag, HttpServletRequest request) {
        return TagVO.objToVo(tag);
    }

    @Override
    public Page<TagVO> getTagVOPage(Page<Tag> tagPage, HttpServletRequest request) {
        List<Tag> tagList = tagPage.getRecords();
        Page<TagVO> tagVOPage = new Page<>(tagPage.getCurrent(), tagPage.getSize(), tagPage.getTotal());
        if (CollUtil.isEmpty(tagList)){
            return tagVOPage;
        }
        // 对象列表 => 封装对象列表
        List<TagVO> tagVOList = tagList.stream().map(TagVO::objToVo).collect(Collectors.toList());
        tagVOList.forEach(tagVO -> {
            tagVOPage.setRecords(tagVOList);
        });
        return tagVOPage;
    }

    @Override
    public QueryWrapper<Tag> getQueryWrapper(TagQueryRequest tagQueryRequest) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        if (tagQueryRequest == null) {
            return queryWrapper;
        }
        Long id = tagQueryRequest.getId();
        String name = tagQueryRequest.getName();
        String sortField = tagQueryRequest.getSortField();
        String sortOrder = tagQueryRequest.getSortOrder();
        queryWrapper.eq(ObjUtil.isNotEmpty(id),"id", tagQueryRequest.getId());
        queryWrapper.like(StrUtil.isNotBlank(name), "name", tagQueryRequest.getName());
        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }
}




