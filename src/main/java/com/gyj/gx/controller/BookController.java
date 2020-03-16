package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.BookVO;
import com.gyj.gx.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("book")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    /**
     * 获取书籍列表（无筛选条件）
     *
     * @return RespEntity 由code message data组成
     */
    @GetMapping("list") // GET请求
    public RespEntity getList() {
        return new RespEntity(RespCode.SUCCESS, bookService.getList());
    }

    /**
     * 获取书籍列表（带删选带分页）
     *
     * @param pageModule
     * @param bookVO
     * @return
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, BookVO bookVO) {
        return new RespEntity(RespCode.SUCCESS, bookService.getPageList(pageModule, bookVO));
    }

    /**
     * 保存书籍
     *
     * @param bookVO
     * @return
     */
    @PostMapping("")
    public RespEntity saveBook(@RequestBody BookVO bookVO) {
        return new RespEntity(RespCode.SUCCESS, bookService.saveBook(bookVO));
    }

}
