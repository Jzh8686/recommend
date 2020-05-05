package com.gyj.gx.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.request.*;
import com.gyj.gx.domain.response.*;

import java.util.List;

public interface ContestService extends IService<ContestEntity> {
    boolean saveContest(ContestVO contestVO);
    PageModule<ContestDTO> getPageList(PageModule pageModule, ContestVO contestVO);
    boolean submitContest(SubmitVO submitVO);
    ReviewDTO getReviewDetail(ReviewVO reviewVO);
    boolean submitReview(ReviewVO reviewVO);
    List<ReviewPageDTO> getReviewPage();//阅卷列表


    List<ResultDTO> getUserPoint(ResultVO resultVO);//获取得分结果
    List<StatisticDTO> getUserStatistic(StatisticVO statisticVO);//获取统计数据

    void addContestSchedules();

    void startContest(ContestVO contestVO);

    void endContest(ContestVO contestVO);

}
