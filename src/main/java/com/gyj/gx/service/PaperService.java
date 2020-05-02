package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.PaperEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.domain.response.PaperDTO;

import java.util.List;

public interface PaperService extends IService<PaperEntity> {
    boolean savePaper(PaperVO paperVO);
    PaperDTO paperDetail(PaperVO paperVO);
    PageModule<PaperDTO> getPageList(PageModule pageModule,PaperVO paperVO);//带分页试卷列表
    boolean deletePaper(PaperVO paperVO);
    List<PaperDTO> getList();
}
