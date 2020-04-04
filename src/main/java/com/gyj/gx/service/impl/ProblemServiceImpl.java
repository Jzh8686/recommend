package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.page.MyPage;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.util.PageUtil;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.ProblemMapper;
import com.gyj.gx.domain.CategoryProblemEntity;
import com.gyj.gx.domain.ProblemEntity;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.ProblemDTO;
import com.gyj.gx.service.CategoryProblemService;
import com.gyj.gx.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, ProblemEntity> implements ProblemService {

    @Autowired
    private CategoryProblemService categoryProblemService;


    @Override
    public PageModule<ProblemDTO> getPageList(PageModule pageModule, ProblemVO problemVO) {
//        Page page = new Page(pageModule.getPageNum(),pageModule.getPageSize());
//        IPage<ProblemDTO> page1 = baseMapper.selectPageVO(page,problemVO);
//        PageModule<ProblemDTO> pm = PageUtil.transToPageModule(page1);
        Integer pageNum = pageModule.getPageNum();
        Integer pageSize = pageModule.getPageSize();

        Integer totalCount = baseMapper.getTotalSearchCount(problemVO);

        PageModule<ProblemDTO> pm = new PageModule<>();
        pm.setPageSize(pageSize);
        pm.setPageNum(pageNum);
        pm.setTotalCount(totalCount);

        int pages = (int) Math.ceil(totalCount*1.0/pageSize);
        pm.setTotalPage(pages);
        pm.setIsMore(pages-pageNum);

        List<Integer> groupCount = baseMapper.getGroupCount(problemVO);

        int skipNumber = groupCount.subList(0,Math.min((pageNum-1)*pageSize,groupCount.size())).stream().mapToInt(Integer::intValue).sum();
        int newPageSize = groupCount.subList((pageNum-1)*pageSize,Math.min(pageNum*pageSize,groupCount.size())).stream().mapToInt(Integer::intValue).sum();

        MyPage myPage = new MyPage();
        myPage.setPageSize(newPageSize);
        myPage.setSkipNumber(skipNumber);

        pm.setItems(baseMapper.selectPageVO(myPage,problemVO));

        return pm;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProblem(ProblemVO problemVO) {
        ValidatorBeanFactory .validate(problemVO, FirstValidator.class);

        if(baseMapper.getRelevantPaperCount(problemVO)!=0)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"不能删除被选入试卷的题目");

        removeById(problemVO.getId());

        categoryProblemService.remove(
                new QueryWrapper<CategoryProblemEntity>().lambda()
                .eq(CategoryProblemEntity::getPid,problemVO.getId())
        ); //把问题原有信息删除
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveProblem(ProblemVO problemVO){
        ValidatorBeanFactory.validate(problemVO, SecondValidator.class);
        if (StringUtils.isNotBlank(problemVO.getOptionA())&& problemVO.getOptionA().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionB())&& problemVO.getOptionB().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionC())&& problemVO.getOptionC().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionD())&& problemVO.getOptionD().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");

        ProblemEntity problemEntity = new ProblemEntity();
        BeanUtils.copyProperties(problemVO,problemEntity);

        //保存对象
        save(problemEntity);

        List<CategoryProblemEntity> cidList = new ArrayList<>();
        for(Integer cid:problemVO.getCategories()){
            CategoryProblemEntity categoryProblemEntity = new CategoryProblemEntity();
            categoryProblemEntity.setCid(cid);
            categoryProblemEntity.setPid(problemEntity.getId());
            cidList.add(categoryProblemEntity);
        }
        categoryProblemService.saveBatch(cidList);

        return true;
    }

    @Override
    public ProblemDTO problemDetail(ProblemVO problemVO) {
        ValidatorBeanFactory.validate(problemVO,FirstValidator.class);

        ProblemEntity problem =getOne(new QueryWrapper<ProblemEntity>().lambda()
                .eq(ProblemEntity::getId,problemVO.getId()));
        if(problem==null){
            throw new BusinessException(RespCode.CUSTOM_ERROR,"问题不存在");
        }

        ProblemDTO problemDTO=new ProblemDTO();
        BeanUtils.copyProperties(problem,problemDTO);

        return problemDTO;
    }

    @Override
    public boolean editProblem(ProblemVO problemVO) {
        ValidatorBeanFactory .validate(problemVO, FirstValidator.class);

        if(baseMapper.getRelevantPaperCount(problemVO)!=0)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"不能删除被选入试卷的题目");

        ValidatorBeanFactory.validate(problemVO, SecondValidator.class);

        ProblemEntity problemExist = getOne(
              new QueryWrapper<ProblemEntity>().lambda()
                .eq(ProblemEntity::getId,problemVO.getId())
        );
        if(problemExist==null){
            throw new BusinessException(RespCode.CUSTOM_ERROR,"问题不存在");
        }

        if (StringUtils.isNotBlank(problemVO.getOptionA())&& problemVO.getOptionA().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionB())&& problemVO.getOptionB().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionC())&& problemVO.getOptionC().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");
        if (StringUtils.isNotBlank(problemVO.getOptionD())&& problemVO.getOptionD().length()>100)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"选项长度超过限制");

        ProblemEntity problemEntity = new ProblemEntity();
        BeanUtils.copyProperties(problemVO,problemEntity);

        //保存对象
        updateById(problemEntity);

        categoryProblemService.remove(
                new QueryWrapper<CategoryProblemEntity>().lambda()
                        .eq(CategoryProblemEntity::getPid,problemVO.getId())
        );

        List<CategoryProblemEntity> cidList = new ArrayList<>();
        for(Integer cid:problemVO.getCategories()){
            CategoryProblemEntity categoryProblemEntity = new CategoryProblemEntity();
            categoryProblemEntity.setCid(cid);
            categoryProblemEntity.setPid(problemEntity.getId());
            cidList.add(categoryProblemEntity);
        }
        categoryProblemService.saveBatch(cidList);

        return true;
    }
}
