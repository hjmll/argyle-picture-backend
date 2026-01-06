package com.argyle.argylepicturebackend.service;

import com.argyle.argylepicturebackend.api.aliyunai.model.CreateOutPaintingTaskResponse;
import com.argyle.argylepicturebackend.model.dto.picture.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.argyle.argylepicturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.argyle.argylepicturebackend.model.entity.User;
import com.argyle.argylepicturebackend.model.vo.PictureVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource, PictureUploadRequest pictureUploadRequest, User loginUser);

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

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(PictureUploadByBatchRequest pictureUploadByBatchRequest, User loginUser);


    @Async
    void clearPictureFile(Picture oldPicture);

    void deletePicture(long pictureId, User loginUser);

    void editPicture(PictureEditRequest pictureEditRequest, User loginUser);

    /**
     * 校验空间图片的权限
     * @param loginUser
     * @param picture
     */
    void checkPictureAuth(User loginUser, Picture picture);

    void deletePicturesBySpaceId(Long spaceId);

    List<PictureVO> searchPictureByColor(Long spaceId, String picColor, User loginUser);

    @Transactional(rollbackFor = Exception.class)
    void editPictureByBatch(PictureEditByBatchRequest pictureEditByBatchRequest, User loginUser);

    /**
     * 创建AI扩图任务
     */
    CreateOutPaintingTaskResponse createPictureOutPaintingTask(CreatePictureOutPaintingTaskRequest createPictureOutPaintingTaskRequest, User loginUser);

    /**
     * AI识别图片元素和信息
     *
     * @param pictureId 图片ID
     * @param loginUser 登录用户
     */
    void recognizePicture(Long pictureId, User loginUser);
}
