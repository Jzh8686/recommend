package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.CategoryEntity;
import com.gyj.gx.domain.request.CategoryVO;
import com.gyj.gx.domain.response.CategoryDTO;

import java.util.List;

public interface CategoryService extends IService<CategoryEntity> {

    List<CategoryDTO> getList();

    boolean saveCategory(CategoryVO categoryVO);

    PageModule<CategoryDTO> getPageList(PageModule pageModule,CategoryVO categoryVO);

}
