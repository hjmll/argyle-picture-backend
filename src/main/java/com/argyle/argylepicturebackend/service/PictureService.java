package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.model.dto.picture.PictureReviewRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.argyle.argylepicturebackend.model.dto.picture.PictureQueryRequest;
import com.argyle.argylepicturebackend.model.dto.picture.PictureUploadRequest;
import com.argyle.argylepicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.argyle.argylepicturebackend.model.entity.User;
import com.argyle.argylepicturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author hjm
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-01-25 17:09:12
*/
public interface PictureService extends IService<Picture> {

    void validPicture(Picture picture);

    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);


    PictureVO getPictureVO(Picture picture, HttpServletRequest request);



    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    void fillReviewParams(Picture picture, User loginUser);
}
