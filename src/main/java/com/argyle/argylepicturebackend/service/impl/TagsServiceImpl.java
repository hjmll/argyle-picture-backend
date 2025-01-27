package com.argyle.argylepicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.argyle.argylepicturebackend.model.entity.Tags;
import com.argyle.argylepicturebackend.service.TagsService;
import com.argyle.argylepicturebackend.mapper.TagsMapper;
import org.springframework.stereotype.Service;

/**
* @author hjm
* @description 针对表【tags(标签表)】的数据库操作Service实现
* @createDate 2025-01-28 00:07:58
*/
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags>
    implements TagsService{

}




