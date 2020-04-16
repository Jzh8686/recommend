package com.gyj.gx.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.request.ContestVO;
import com.gyj.gx.domain.response.ContestDTO;

public interface ContestService extends IService<ContestEntity> {
    boolean saveContest(ContestVO contestVO);
    PageModule<ContestDTO> getPageList(PageModule pageModule, ContestVO contestVO);
}
