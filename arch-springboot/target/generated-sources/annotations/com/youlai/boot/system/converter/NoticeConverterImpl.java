package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.NoticeBO;
import com.youlai.boot.system.model.entity.Notice;
import com.youlai.boot.system.model.form.NoticeForm;
import com.youlai.boot.system.model.vo.NoticeDetailVO;
import com.youlai.boot.system.model.vo.NoticePageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NoticeConverterImpl implements NoticeConverter {

    @Override
    public NoticeForm toForm(Notice entity) {
        if ( entity == null ) {
            return null;
        }

        NoticeForm noticeForm = new NoticeForm();

        noticeForm.setId( entity.getId() );
        noticeForm.setTitle( entity.getTitle() );
        noticeForm.setContent( entity.getContent() );
        noticeForm.setType( entity.getType() );
        noticeForm.setLevel( entity.getLevel() );
        noticeForm.setTargetType( entity.getTargetType() );

        noticeForm.setTargetUserIds( cn.hutool.core.util.StrUtil.split(entity.getTargetUserIds(),",") );

        return noticeForm;
    }

    @Override
    public Notice toEntity(NoticeForm formData) {
        if ( formData == null ) {
            return null;
        }

        Notice notice = new Notice();

        notice.setId( formData.getId() );
        notice.setTitle( formData.getTitle() );
        notice.setContent( formData.getContent() );
        notice.setType( formData.getType() );
        notice.setLevel( formData.getLevel() );
        notice.setTargetType( formData.getTargetType() );

        notice.setTargetUserIds( cn.hutool.core.collection.CollUtil.join(formData.getTargetUserIds(),",") );

        return notice;
    }

    @Override
    public NoticePageVO toPageVo(NoticeBO bo) {
        if ( bo == null ) {
            return null;
        }

        NoticePageVO noticePageVO = new NoticePageVO();

        noticePageVO.setId( bo.getId() );
        noticePageVO.setTitle( bo.getTitle() );
        noticePageVO.setPublishStatus( bo.getPublishStatus() );
        noticePageVO.setType( bo.getType() );
        noticePageVO.setPublisherName( bo.getPublisherName() );
        noticePageVO.setLevel( bo.getLevel() );
        noticePageVO.setPublishTime( bo.getPublishTime() );
        noticePageVO.setTargetType( bo.getTargetType() );
        noticePageVO.setCreateTime( bo.getCreateTime() );
        noticePageVO.setRevokeTime( bo.getRevokeTime() );

        return noticePageVO;
    }

    @Override
    public Page<NoticePageVO> toPageVo(Page<NoticeBO> noticePage) {
        if ( noticePage == null ) {
            return null;
        }

        Page<NoticePageVO> page = new Page<NoticePageVO>();

        page.setPages( noticePage.getPages() );
        page.setRecords( noticeBOListToNoticePageVOList( noticePage.getRecords() ) );
        page.setTotal( noticePage.getTotal() );
        page.setSize( noticePage.getSize() );
        page.setCurrent( noticePage.getCurrent() );

        return page;
    }

    @Override
    public NoticeDetailVO toDetailVO(NoticeBO noticeBO) {
        if ( noticeBO == null ) {
            return null;
        }

        NoticeDetailVO noticeDetailVO = new NoticeDetailVO();

        noticeDetailVO.setId( noticeBO.getId() );
        noticeDetailVO.setTitle( noticeBO.getTitle() );
        noticeDetailVO.setContent( noticeBO.getContent() );
        noticeDetailVO.setType( noticeBO.getType() );
        noticeDetailVO.setPublisherName( noticeBO.getPublisherName() );
        noticeDetailVO.setLevel( noticeBO.getLevel() );
        noticeDetailVO.setPublishStatus( noticeBO.getPublishStatus() );
        noticeDetailVO.setPublishTime( noticeBO.getPublishTime() );

        return noticeDetailVO;
    }

    protected List<NoticePageVO> noticeBOListToNoticePageVOList(List<NoticeBO> list) {
        if ( list == null ) {
            return null;
        }

        List<NoticePageVO> list1 = new ArrayList<NoticePageVO>( list.size() );
        for ( NoticeBO noticeBO : list ) {
            list1.add( toPageVo( noticeBO ) );
        }

        return list1;
    }
}
