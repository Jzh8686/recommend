package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.*;
import com.gyj.gx.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("contest")
public class ContestController extends BaseController {
    @Autowired
    private ContestService contestService;

    /**
     * 新建竞赛
     *
     * @param
     * @return
     */
    @PostMapping("add")
    public RespEntity saveContest(@RequestBody ContestVO contestVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.saveContest(contestVO));
    }

    /**
     * 带分页竞赛列表
     *
     * @param pageModule contestVO
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, ContestVO contestVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.getPageList(pageModule, contestVO));
    }

    /**
     * 提交竞赛答案
     */
    @PostMapping("submit")
    public RespEntity submitContest(@RequestBody SubmitVO submitVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.submitContest(submitVO));
    }

    /**
     * 获取阅卷详情
     */
    @GetMapping("review")
    public RespEntity getReviewDetail(ReviewVO reviewVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.getReviewDetail(reviewVO));
    }

    /**
     * 提交阅卷分数
     */
    @PostMapping("point")
    public RespEntity submitResult(@RequestBody ReviewVO reviewVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.submitReview(reviewVO));
    }

    /**
     * @param
     * @ 阅卷列表
     */
    @GetMapping("reviewPage")
    public RespEntity getReviewPage() {
        return new RespEntity(RespCode.SUCCESS, contestService.getReviewPage());
    }

    /**
    * @ 结果-得分
    * @param
    */
    @GetMapping("point")
    public RespEntity getUserPoint(ResultVO resultVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.getUserPoint(resultVO));
    }
    /**
     * @ 结果-数据分析
     * @param
     */
    @GetMapping("statistic")
    public RespEntity getUserStatistic(StatisticVO statisticVO) {
        return new RespEntity(RespCode.SUCCESS, contestService.getUserStatistic(statisticVO));
    }

}