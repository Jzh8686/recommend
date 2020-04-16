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
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.ContestMapper;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.request.ContestVO;
import com.gyj.gx.domain.response.ContestDTO;
import com.gyj.gx.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContestServiceImpl extends ServiceImpl<ContestMapper, ContestEntity> implements ContestService {
    @Override
    public boolean saveContest(ContestVO contestVO) {
        ValidatorBeanFactory.validate(contestVO, FirstValidator.class);

        ContestEntity duplicate = getOne(
                new QueryWrapper<ContestEntity>().lambda()
                .eq(ContestEntity::getContestName,contestVO.getContestName())
        );
        if(duplicate!=null)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"竞赛名重复");
        ContestEntity contestEntity = new ContestEntity();
        BeanUtils.copyProperties(contestVO,contestEntity);
        return save(contestEntity);
    }

    @Override
    public PageModule<ContestDTO> getPageList(PageModule pageModule, ContestVO contestVO) {
        Page<ContestEntity> page = new Page(pageModule.getPageNum(),pageModule.getPageSize());
        LambdaQueryWrapper<ContestEntity> query = new QueryWrapper<ContestEntity>().lambda();
        if(StringUtils.isNotBlank(contestVO.getContestName()))
            query.like(ContestEntity::getContestName,contestVO.getContestName());
            query.orderByDesc(ContestEntity::getStartTime);
            PageModule pm = PageUtil.transToPageModule(page(page,query));
            Object items = pm.getItems().stream().map(x->{
                ContestEntity contestEntity = (ContestEntity)x;
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
            pm.setItems((List)items);
            return pm;
    }

}
