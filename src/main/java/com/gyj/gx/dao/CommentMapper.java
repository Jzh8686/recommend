package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyj.gx.domain.CommentEntity;
import com.gyj.gx.domain.request.CommentVO;
import com.gyj.gx.domain.response.CommentDTO;
import com.gyj.gx.domain.response.HotCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
    List<CommentDTO> getCommentRelatedToProblem(@Param("map") CommentVO commentVO);

    Integer likeComment(@Param("map") CommentVO commentVO);

    Integer unlikeComment(@Param("map") CommentVO commentVO);

    List<HotCommentDTO> hotComment();
}
