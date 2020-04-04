package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.ProblemEntity;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.ProblemDTO;

public interface ProblemService extends IService<ProblemEntity> {
    PageModule<ProblemDTO> getPageList(PageModule pageModule, ProblemVO problemVO);
    boolean deleteProblem(ProblemVO problemVO);
    boolean saveProblem(ProblemVO problemVO);
    ProblemDTO problemDetail(ProblemVO problemVO);
    boolean editProblem(ProblemVO problemVO);
}
