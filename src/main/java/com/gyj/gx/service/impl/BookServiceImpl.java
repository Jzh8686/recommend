package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.util.PageUtil;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.BookMapper;
import com.gyj.gx.domain.BookEntity;
import com.gyj.gx.domain.request.BookVO;
import com.gyj.gx.domain.response.BookDTO;
import com.gyj.gx.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity> implements BookService {

    @Override
    public List<BookDTO> getList() {
        // list获取所有的bookEntity
        List<BookEntity> bookEntityList = list();

        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity bookEntity : bookEntityList) {
            //为了安全性角度考虑，不暴露主键，隐去ID，这一部实际使用中根据情况取舍
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(bookEntity, bookDTO);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    @Override
    public PageModule getPageList(PageModule<BookEntity> pageModule, BookVO bookVO) {

        // 分页参数，页码，每页大小
        Page<BookEntity> page = new Page<>(pageModule.getPageNum(), pageModule.getPageSize());
        // 查询条件
        LambdaQueryWrapper<BookEntity> query = new QueryWrapper<BookEntity>().lambda();
        // 一步步拼装，按ID升序排列
        query.orderByAsc(BookEntity::getId);
        // 如果书名不为空，则模糊查询
        if (!StringUtils.isBlank(bookVO.getName()))
            query.or().like(BookEntity::getName,bookVO.getName());
        // 如果作者不为空，则模糊查询
        if (!StringUtils.isBlank(bookVO.getAuthor()))
            query.or().like(BookEntity::getAuthor,bookVO.getAuthor());
        // 分页查询
        IPage<BookEntity> iPage = page(page,query);
        // 将结果转化回pageModule
        PageModule pm = PageUtil.transToPageModule(iPage);
        // 清洗字段，不是必须的，也可以在上面的page()这一步进行
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Object bookEntity:pm.getItems()){
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(bookEntity,bookDTO);
            bookDTOList.add(bookDTO);
        }
        pm.setItems(bookDTOList);

        return pm;
    }

    @Override
    public boolean saveBook(BookVO bookVO) {
        // 验证对象中所有属于FirstValidator的字段
        ValidatorBeanFactory.validate(bookVO, FirstValidator.class);

        //只取需要的字段
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookVO,bookEntity);

        //保存对象
        save(bookEntity);
        return true;
    }
}
