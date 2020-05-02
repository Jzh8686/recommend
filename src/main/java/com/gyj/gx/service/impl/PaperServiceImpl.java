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
import com.gyj.gx.base.util.validator.ThirdValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.PaperMapper;
import com.gyj.gx.domain.ContestEntity;
import com.gyj.gx.domain.PaperEntity;
import com.gyj.gx.domain.ProblemEntity;
import com.gyj.gx.domain.ProblemPaperEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.PaperDTO;
import com.gyj.gx.service.ContestService;
import com.gyj.gx.service.PaperService;
import com.gyj.gx.service.ProblemPaperService;
import com.gyj.gx.service.ProblemService;
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
public class PaperServiceImpl extends ServiceImpl<PaperMapper, PaperEntity> implements PaperService {

    @Autowired
    private ProblemPaperService problemPaperService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ProblemService problemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePaper(PaperVO paperVO) {
        ValidatorBeanFactory.validate(paperVO, FirstValidator.class);
        ValidatorBeanFactory.validateList(paperVO.getProblems(), ThirdValidator.class);

        PaperEntity duplicate = getOne(
                new QueryWrapper<PaperEntity>().lambda()
                        .eq(PaperEntity::getPaperName, paperVO.getPaperName())
        );
        if (duplicate != null)
            throw new BusinessException(RespCode.CUSTOM_ERROR, "试卷名重复");

        PaperEntity paperEntity = new PaperEntity();
        BeanUtils.copyProperties(paperVO, paperEntity);

        save(paperEntity);

        List<Integer> problemList = new ArrayList<>();
        Map<Integer,ProblemPaperEntity> map = new HashMap<>();
        for (ProblemVO problemVO : paperVO.getProblems()) {
            ProblemPaperEntity entity = new ProblemPaperEntity();
            entity.setPbid(problemVO.getId());
            entity.setPpid(paperEntity.getId());
            entity.setPoint(problemVO.getPoint());
            problemList.add(problemVO.getId());
            map.put(problemVO.getId(),entity);
        }
        List<ProblemEntity> problemEntities = problemService.listByIds(problemList);
        for(ProblemEntity problemEntity:problemEntities){
            ProblemPaperEntity problemPaperEntity = map.get(problemEntity.getId());
            problemPaperEntity.setAnswer(problemEntity.getAnswer());
        }
        problemPaperService.saveBatch(map.values());

        return true;
    }

    @Override
    public PaperDTO paperDetail(PaperVO paperVO) {
        ValidatorBeanFactory.validate(paperVO, SecondValidator.class);

        PaperEntity paperEntity = getOne(new QueryWrapper<PaperEntity>().lambda()
                .eq(PaperEntity::getId, paperVO.getId()));
        if (paperEntity == null) {
            throw new BusinessException(RespCode.CUSTOM_ERROR, "试卷不存在");
        }
        PaperDTO paperDTO = new PaperDTO();
        BeanUtils.copyProperties(paperEntity, paperDTO);

        paperDTO.setProblems(baseMapper.getProblemRelatedToPaper(paperVO));
        return paperDTO;
    }

    @Override
    public PageModule<PaperDTO> getPageList(PageModule pageModule, PaperVO paperVO) {
        Page<PaperEntity> page = new Page(pageModule.getPageNum(), pageModule.getPageSize());
        LambdaQueryWrapper<PaperEntity> query = new QueryWrapper<PaperEntity>().lambda();
        if (StringUtils.isNotBlank(paperVO.getPaperName()))
            query.like(PaperEntity::getPaperName, paperVO.getPaperName());
        query.orderByDesc(PaperEntity::getModifyDate);
        PageModule pm = PageUtil.transToPageModule(page(page, query));
        Object items = pm.getItems().stream().map(x -> {
            PaperEntity paperEntity = (PaperEntity) x;
            PaperDTO paperDTO = new PaperDTO();
            paperDTO.setId(paperEntity.getId());
            paperDTO.setPaperName(paperEntity.getPaperName());
            paperDTO.setModifyDate(paperEntity.getModifyDate());
            return paperDTO;
        }).collect(Collectors.toList());
        pm.setItems((List) items);
        return pm;
    }

    @Override
    public boolean deletePaper(PaperVO paperVO) {
        ValidatorBeanFactory.validate(paperVO, SecondValidator.class);
        List contests = contestService.list(
                new QueryWrapper<ContestEntity>()
                        .lambda()
                        .eq(ContestEntity::getPaper, paperVO.getId())
        );
        if (contests != null && contests.size() > 0)
            throw new BusinessException(RespCode.CUSTOM_ERROR, "不能删除已被选入竞赛的试卷");
        removeById(paperVO.getId());

        return true;
    }

    @Override
    public List<PaperDTO> getList() {
        List<PaperEntity> entities = list();
        List<PaperDTO> dtos = new ArrayList<>(entities.size());
        for (PaperEntity pe : entities) {
            PaperDTO paperDTO = new PaperDTO();

            paperDTO.setId(pe.getId());
            paperDTO.setPaperName(pe.getPaperName());
            dtos.add(paperDTO);
        }
        return dtos;
    }
}
