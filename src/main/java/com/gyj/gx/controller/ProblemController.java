package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.CategoryVO;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("problem")
public class ProblemController extends BaseController{
    @Autowired
    private ProblemService problemService;
    /**
     * 带分页带查询列表
     * @param pageModule
     * @param problemVO
     * @return
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, ProblemVO problemVO){
        return new RespEntity(RespCode.SUCCESS, problemService.getPageList(pageModule, problemVO));
    }
    /**
     * 删除题目
     * 如果题目未被选入试卷，可以删除
     * 如果题目被选入，不能删除
     * @param problemVO
     * @return
     */
    @DeleteMapping("")
    public RespEntity deleteProblem(ProblemVO problemVO){
        return  new RespEntity(RespCode.SUCCESS,problemService.deleteProblem(problemVO));
    }

    /**
     * 添加分类
     *
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity saveProblem(@RequestBody ProblemVO problemVO) {
        return new RespEntity(RespCode.SUCCESS, problemService.saveProblem(problemVO));
    }

}
