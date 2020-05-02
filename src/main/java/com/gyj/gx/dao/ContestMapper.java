package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.base.page.MyPage;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.request.ResultVO;
import com.gyj.gx.domain.request.ReviewVO;
import com.gyj.gx.domain.request.StatisticVO;
import com.gyj.gx.domain.response.ResultDTO;
import com.gyj.gx.domain.response.ReviewDTO;
import com.gyj.gx.domain.response.ReviewPageDTO;
import com.gyj.gx.domain.response.StatisticDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContestMapper extends BaseMapper<ContestEntity> {
    ReviewDTO getReviewContest(@Param("map") ReviewVO reviewVO); //阅卷详情

    List<ReviewPageDTO> selectReviewList();//阅卷列表

    List<ResultDTO> getUserPoint(@Param("map") ResultVO resultVO); //获取得分结果

    List<StatisticDTO> getUserStatistic(@Param("map") StatisticVO statisticVO);//获取统计数据
}
