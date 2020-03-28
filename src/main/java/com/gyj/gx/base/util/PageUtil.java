package com.gyj.gx.base.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.base.page.PageModule;

public class PageUtil {
    public static <T> PageModule<T> transToPageModule(IPage<T> page){
        PageModule pageModule = new PageModule();
        pageModule.setTotalCount((int) page.getTotal());
        pageModule.setPageNum((int) page.getCurrent());
        pageModule.setPageSize((int) page.getSize());
        pageModule.setItems(page.getRecords());
        pageModule.setTotalPage((int) page.getPages());
        pageModule.setIsMore(pageModule.getTotalCount()>pageModule.getPageNum()*pageModule.getPageSize()?1:0);
        pageModule.setStartIndex((int) page.offset());
        return pageModule;
    }
}
