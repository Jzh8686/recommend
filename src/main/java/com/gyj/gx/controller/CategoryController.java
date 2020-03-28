package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.CategoryVO;
import com.gyj.gx.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController{
    @Autowired
    private CategoryService categoryService;
    /**
     * 获取分类列表(无筛选条件)
     */
    @GetMapping("list")
    public RespEntity getList(){
        return  new RespEntity(RespCode.SUCCESS,categoryService.getList());
    }
    /**
     * 添加分类
     *
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity saveCategory(@RequestBody CategoryVO categoryVO) {
        return new RespEntity(RespCode.SUCCESS, categoryService.saveCategory(categoryVO));
    }

    /**
     * 带分页带查询列表
     * @param pageModule
     * @param categoryVO
     * @return
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule,CategoryVO categoryVO){
        return new RespEntity(RespCode.SUCCESS, categoryService.getPageList(pageModule, categoryVO));
    }
}
