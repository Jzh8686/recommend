package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.util.PageUtil;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.ForthValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.ContestMapper;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.ProblemPaperEntity;
import com.gyj.gx.domain.UserContestEntity;
import com.gyj.gx.domain.request.*;
import com.gyj.gx.domain.response.*;
import com.gyj.gx.service.ContestService;
import com.gyj.gx.service.ProblemPaperService;
import com.gyj.gx.service.UserContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContestServiceImpl extends ServiceImpl<ContestMapper, ContestEntity> implements ContestService {
    @Autowired
    private ProblemPaperService problemPaperService;

    @Autowired
    private UserContestService userContestService;

    @Override
    public boolean saveContest(ContestVO contestVO) {
        ValidatorBeanFactory.validate(contestVO, FirstValidator.class);

        ContestEntity duplicate = getOne(
                new QueryWrapper<ContestEntity>().lambda()
                        .eq(ContestEntity::getContestName, contestVO.getContestName())
        );
        if (duplicate != null)
            throw new BusinessException(RespCode.CUSTOM_ERROR, "竞赛名重复");
        ContestEntity contestEntity = new ContestEntity();
        BeanUtils.copyProperties(contestVO, contestEntity);
        return save(contestEntity);
    }

    @Override
    public PageModule<ContestDTO> getPageList(PageModule pageModule, ContestVO contestVO) {
        Page<ContestEntity> page = new Page(pageModule.getPageNum(), pageModule.getPageSize());
        LambdaQueryWrapper<ContestEntity> query = new QueryWrapper<ContestEntity>().lambda();
        if (StringUtils.isNotBlank(contestVO.getContestName()))
            query.like(ContestEntity::getContestName, contestVO.getContestName());
        query.orderByDesc(ContestEntity::getStartTime);
        PageModule pm = PageUtil.transToPageModule(page(page, query));
        Object items = pm.getItems().stream().map(x -> {
            ContestEntity contestEntity = (ContestEntity) x;
            ContestDTO contestDTO = new ContestDTO();
            contestDTO.setId(contestEntity.getId());
            contestDTO.setPaper(contestEntity.getPaper());
            contestDTO.setContestName(contestEntity.getContestName());
            contestDTO.setLimitTime(contestEntity.getLimitTime());
            contestDTO.setStartTime(contestEntity.getStartTime());
            contestDTO.setEndTime(contestEntity.getEndTime());
            contestDTO.setState(contestEntity.getState());
            return contestDTO;
        }).collect(Collectors.toList());
        pm.setItems((List) items);
        return pm;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitContest(SubmitVO submitVO) {
        ValidatorBeanFactory.validate(submitVO, FirstValidator.class);
        ValidatorBeanFactory.validateList(submitVO.getProblems(), ForthValidator.class);

        List<UserContestEntity> ucey = userContestService.list(
                new QueryWrapper<UserContestEntity>().lambda()
                        .eq(UserContestEntity::getCid, submitVO.getCid())
                        .eq(UserContestEntity::getUid, submitVO.getUid())
        );
        if (!ucey.isEmpty())
            throw new BusinessException(RespCode.CUSTOM_ERROR, "请勿重复提交");

        Map<Integer, ProblemPaperEntity> map = new HashMap<>();

        ContestEntity contestEntity = getOne(
                new QueryWrapper<ContestEntity>().lambda()
                        .eq(ContestEntity::getId, submitVO.getCid())
        );

        List<ProblemPaperEntity> ppe = problemPaperService.list(
                new QueryWrapper<ProblemPaperEntity>().lambda()
                        .eq(ProblemPaperEntity::getPpid, contestEntity.getPaper())
        );

        for (ProblemPaperEntity pp : ppe) {
            map.put(pp.getPbid(), pp);
        }

        List<UserContestEntity> uce = new ArrayList<>();

        for (ProblemVO problemVO : submitVO.getProblems()) {
            UserContestEntity userContestEntity = new UserContestEntity();
            userContestEntity.setUid(submitVO.getUid());
            userContestEntity.setCid(submitVO.getCid());
            userContestEntity.setPid(problemVO.getId());
            userContestEntity.setAnswer(problemVO.getAnswer().trim());
            if (problemVO.getProblemType() == 0) {
                ProblemPaperEntity pp = map.get(problemVO.getId());
                if (userContestEntity.getAnswer().equals(pp.getAnswer())) {
                    userContestEntity.setPoint(pp.getPoint());
                    userContestEntity.setCorrect(1);
                } else {
                    userContestEntity.setCorrect(0);
                    userContestEntity.setPoint(0.0);
                }
            }
            uce.add(userContestEntity);
        }

        userContestService.saveBatch(uce);

        return true;
    }

    @Override
    public ReviewDTO getReviewDetail(ReviewVO reviewVO) {
        ValidatorBeanFactory.validate(reviewVO, FirstValidator.class);
        return baseMapper.getReviewContest(reviewVO);
    }

    @Override
    public boolean submitReview(ReviewVO reviewVO) {
        ValidatorBeanFactory.validate(reviewVO, SecondValidator.class);

       UserContestEntity ucety =  userContestService.getOne(
                new QueryWrapper<UserContestEntity>().lambda()
                        .eq(UserContestEntity::getId, reviewVO.getRid()));

       if(ucety==null){
           throw new BusinessException(RespCode.CUSTOM_ERROR,"无效的答题记录");
       }

       ContestEntity ce = getOne(
               new QueryWrapper<ContestEntity>().lambda()
               .eq(ContestEntity::getId,ucety.getCid()));

       ProblemPaperEntity ppe =  problemPaperService.getOne(
               new QueryWrapper<ProblemPaperEntity>().lambda()
                .eq(ProblemPaperEntity::getPbid,ucety.getPid())
               .eq(ProblemPaperEntity::getPpid,ce.getPaper()));

       if(ppe==null){
           throw new BusinessException(RespCode.CUSTOM_ERROR,"问题为空");
       }
        if(reviewVO.getPoint()>ppe.getPoint()){
            throw new BusinessException(RespCode.CUSTOM_ERROR,"分数超出上限");
        }

        UserContestEntity uce = new UserContestEntity();
        uce.setId(reviewVO.getRid());
        uce.setCorrect(reviewVO.getPoint().equals(ppe.getPoint()) ?1:0);
        uce.setPoint(reviewVO.getPoint());
        return userContestService.updateById(uce);
    }

    @Override
    public List<ReviewPageDTO> getReviewPage() {
        return baseMapper.selectReviewList();
    }

    @Override
    public List<ResultDTO> getUserPoint(ResultVO resultVO) {
        ValidatorBeanFactory.validate(resultVO,ForthValidator.class);
        return baseMapper.getUserPoint(resultVO);
    }

    @Override
    public List<StatisticDTO> getUserStatistic(StatisticVO statisticVO) {
        ValidatorBeanFactory.validate(statisticVO,ForthValidator.class);
        return baseMapper.getUserStatistic(statisticVO);
    }
}
