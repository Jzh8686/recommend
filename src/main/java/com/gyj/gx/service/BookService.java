package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.BookEntity;
import com.gyj.gx.domain.request.BookVO;
import com.gyj.gx.domain.response.BookDTO;

import java.util.List;

public interface BookService extends IService<BookEntity> {

    List<BookDTO> getList();

    PageModule getPageList(PageModule<BookEntity> pageModule, BookVO bookVO);

    boolean saveBook(BookVO bookVO);

}
