package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("paper")
public class PaperController extends BaseController{
    @Autowired
    private PaperService paperService;

    /**
     * 添加试卷
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity savePaper(@RequestBody PaperVO paperVO){
        return new RespEntity(RespCode.SUCCESS,paperService.savePaper(paperVO));
    }
    /**
     * 试卷详情
     * @param paperVO
     * @return
     */
    @GetMapping("detail")
    public RespEntity paperDetail(PaperVO paperVO){
        return new RespEntity(RespCode.SUCCESS,paperService.paperDetail(paperVO));
    }
    /**
     * 带分页试卷列表
     * @param pageModule
     * paperVO
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, PaperVO paperVO){
        return new RespEntity(RespCode.SUCCESS, paperService.getPageList(pageModule, paperVO));
    }

}
