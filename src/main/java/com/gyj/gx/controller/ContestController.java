package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.ContestVO;
import com.gyj.gx.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("contest")
public class ContestController extends BaseController {
    @Autowired
    private ContestService contestService;
    /**
     * 新建竞赛
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity saveContest(@RequestBody ContestVO contestVO){
        return new RespEntity(RespCode.SUCCESS,contestService.saveContest(contestVO));
    }
    /**
     * 带分页竞赛列表
     * @param pageModule
     * contestVO
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, ContestVO contestVO){
        return new RespEntity(RespCode.SUCCESS, contestService.getPageList(pageModule, contestVO));
    }

}
