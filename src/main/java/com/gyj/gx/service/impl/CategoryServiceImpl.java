package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import com.gyj.gx.dao.CategoryMapper;
import com.gyj.gx.domain.CategoryEntity;
import com.gyj.gx.domain.request.CategoryVO;
import com.gyj.gx.domain.response.CategoryDTO;
import com.gyj.gx.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {
    @Override
    public List<CategoryDTO> getList(CategoryVO categoryVO) {
        ValidatorBeanFactory.validate(categoryVO, ThirdValidator.class);

        LambdaQueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<CategoryEntity>().lambda();
        if (categoryVO.getType()!=4)
            queryWrapper.eq(CategoryEntity::getType,categoryVO.getType());

        // list获取所有的categoryEntity
        List<CategoryEntity> categoryEntityList = list(queryWrapper);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntityList) {
            //为了安全性角度考虑，不暴露主键，隐去ID，这一部实际使用中根据情况取舍
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(categoryEntity, categoryDTO);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
    @Override
    public boolean saveCategory(CategoryVO categoryVO){
        // 验证对象中所有属于FirstValidator的字段
        ValidatorBeanFactory.validate(categoryVO, FirstValidator.class);


        CategoryEntity duplicate = getOne(
                new QueryWrapper<CategoryEntity>().lambda()
                .eq(CategoryEntity::getSubtype,categoryVO.getSubtype())
        );
        if(duplicate!=null)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"存在重复的分类名");

        //只取需要的字段
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryVO,categoryEntity);

        //保存对象
        save(categoryEntity);
        return true;
    }

    @Override
    public PageModule<CategoryDTO> getPageList(PageModule pageModule, CategoryVO categoryVO) {
        Page page = new Page(pageModule.getPageNum(),pageModule.getPageSize());
        IPage<CategoryDTO> page1 = baseMapper.selectPageVO(page,categoryVO);
        PageModule<CategoryDTO> pm = PageUtil.transToPageModule(page1);

        return pm;
    }

    @Override
    public boolean deleteCategory(CategoryVO categoryVO) {
        ValidatorBeanFactory.validate(categoryVO, SecondValidator.class);

        if(baseMapper.getProblemNum(categoryVO)!=0)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"不能删除已有题目的分类");

        return removeById(categoryVO.getId());
    }
}
