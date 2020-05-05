package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ThirdValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.CommentMapper;
import com.gyj.gx.domain.CommentEntity;
import com.gyj.gx.domain.LikeEntity;
import com.gyj.gx.domain.request.CommentVO;
import com.gyj.gx.domain.response.CommentDTO;
import com.gyj.gx.domain.response.HotCommentDTO;
import com.gyj.gx.service.CommentService;
import com.gyj.gx.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Autowired
    private LikeService likeService;

    @Override
    public boolean addComment(CommentVO commentVO) {
        ValidatorBeanFactory.validate(commentVO, FirstValidator.class);
        ValidatorBeanFactory.validate(commentVO, SecondValidator.class);
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setFromUid(commentVO.getFromUid());
        commentEntity.setCommentTime(new Date());
        commentEntity.setPid(commentVO.getPid());
        commentEntity.setContent(commentVO.getContent());
        commentEntity.setReplyToCid(commentVO.getReplyToCid());
        commentEntity.setReplyToUid(commentVO.getReplyToUid());
        return save(commentEntity);
    }

    @Override
    public List<CommentDTO> getCommentList(CommentVO commentVO) {
        ValidatorBeanFactory.validate(commentVO, SecondValidator.class);
        List<CommentDTO> commentList = baseMapper.getCommentRelatedToProblem(commentVO);

        Map<Integer, CommentDTO> map = new HashMap<>();
        // 把所有的父评论都添加到map中
        for (CommentDTO comment : commentList) {
            if (comment.getReplyToCid() == null) {
                map.put(comment.getId(), comment);
            }
        }
        // 将所有的子评论添加到父评论的children中
        for (CommentDTO comment : commentList) {
            if (comment.getReplyToCid() != null) {
                CommentDTO parent = map.get(comment.getReplyToCid());
                List<CommentDTO> children = parent.getChildren() == null ? new LinkedList<>() : parent.getChildren();
                children.add(comment);
                parent.setChildren(children);
            }
        }
        commentList.clear();
        commentList.addAll(map.values());
        return commentList;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer like(CommentVO commentVO) {
        ValidatorBeanFactory.validate(commentVO, ThirdValidator.class);
        LikeEntity like = likeService.getOne(
                new QueryWrapper<LikeEntity>().lambda()
                .eq(LikeEntity::getUid,commentVO.getFromUid())
                .eq(LikeEntity::getCid,commentVO.getId())
        );

        if(like!=null)
            throw  new BusinessException(RespCode.CUSTOM_ERROR,"您已点赞");

        like = new LikeEntity();
        like.setUid(commentVO.getFromUid());
        like.setCid(commentVO.getId());
        likeService.save(like);

        return baseMapper.likeComment(commentVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer unlike(CommentVO commentVO) {
        ValidatorBeanFactory.validate(commentVO, ThirdValidator.class);
        LikeEntity like = likeService.getOne(
                new QueryWrapper<LikeEntity>().lambda()
                        .eq(LikeEntity::getUid,commentVO.getFromUid())
                        .eq(LikeEntity::getCid,commentVO.getId())
        );

        if(like==null)
            throw  new BusinessException(RespCode.CUSTOM_ERROR,"您还未点赞这条评论");
        likeService.removeById(like.getId());

        return baseMapper.unlikeComment(commentVO);
    }

    @Override
    public List<HotCommentDTO> hotComment() {
        return baseMapper.hotComment();
    }

}
