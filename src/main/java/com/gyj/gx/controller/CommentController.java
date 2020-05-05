package com.gyj.gx.controller;

import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.CommentVO;
import com.gyj.gx.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity addComment(@RequestBody CommentVO commentVO) {
        return new RespEntity(RespCode.SUCCESS, commentService.addComment(commentVO));
    }

    /**
     * @param
     * @ 获取评论列表
     */
    @GetMapping("list")
    public RespEntity getCommentList(CommentVO commentVO) {
        return new RespEntity(RespCode.SUCCESS, commentService.getCommentList(commentVO));
    }
    /**
     * 点赞
     *
     * @param
     * @return
     */
    @PostMapping("like")
    public RespEntity like(@RequestBody CommentVO commentVO) {
        return new RespEntity(RespCode.SUCCESS, commentService.like(commentVO));
    }

    /**
     * 取消点赞
     *
     * @param
     * @return
     */
    @PostMapping("unlike")
    public RespEntity unlike(@RequestBody CommentVO commentVO) {
        return new RespEntity(RespCode.SUCCESS, commentService.unlike(commentVO));
    }

    @GetMapping("hot")
    public RespEntity hotComment(){
        return new RespEntity(RespCode.SUCCESS,commentService.hotComment());
    }
}
