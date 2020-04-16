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
public class PaperController extends BaseController {
    @Autowired
    private PaperService paperService;

    /**
     * 添加试卷
     *
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity savePaper(@RequestBody PaperVO paperVO) {
        return new RespEntity(RespCode.SUCCESS, paperService.savePaper(paperVO));
    }

    /**
     * 试卷详情
     *
     * @param paperVO
     * @return
     */
    @GetMapping("detail")
    public RespEntity paperDetail(PaperVO paperVO) {
        return new RespEntity(RespCode.SUCCESS, paperService.paperDetail(paperVO));
    }

    /**
     * 带分页试卷列表
     *
     * @param pageModule paperVO
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, PaperVO paperVO) {
        return new RespEntity(RespCode.SUCCESS, paperService.getPageList(pageModule, paperVO));
    }

    /**
     * 试卷列表
     */
    @GetMapping("list")
    public RespEntity getList() {
        return new RespEntity(RespCode.SUCCESS, paperService.getList());
    }


    /**
     * 删除试卷
     * 如果试卷未被选入竞赛，可以删除
     * 如果试卷被选入，不能删除
     * @param paperVO
     * @return
     */
    @DeleteMapping("")
    public RespEntity deletePaper(PaperVO paperVO){
        return  new RespEntity(RespCode.SUCCESS,paperService.deletePaper(paperVO));
    }

}
