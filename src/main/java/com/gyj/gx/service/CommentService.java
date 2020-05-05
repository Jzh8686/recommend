package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.domain.CommentEntity;
import com.gyj.gx.domain.request.CommentVO;
import com.gyj.gx.domain.response.CommentDTO;
import com.gyj.gx.domain.response.HotCommentDTO;

import java.util.List;

public interface CommentService extends IService<CommentEntity> {
    boolean addComment(CommentVO commentVO);
    List<CommentDTO> getCommentList(CommentVO commentVO);
    Integer like(CommentVO commentVO);
    Integer unlike(CommentVO commentVO);

    List<HotCommentDTO> hotComment();
}
