package com.gyj.gx.base.page;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class PageModule<T> {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页显示的总条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer totalCount;
    /**
     * 是否有下一页
     */
    private Integer isMore;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 开始索引
     */
    private Integer startIndex;
    /**
     * 分页结果
     */
    private List<T> items;
    /**
     * 排序字段
     */
    @JsonIgnore
    private String orderProperty;
    /**
     * 排序方式（asc,desc） 默认 asc
     */
    @JsonIgnore
    private String orderDirection = "asc";

    public PageModule() {
        super();
    }

    public PageModule(Integer pageNum, Integer pageSize, Integer totalCount) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
        this.startIndex = (this.pageNum-1)*this.pageSize;
        this.isMore = this.pageNum >= this.totalPage?0:1;
    }

    public PageModule(Integer pageNum, Integer pageSize, Integer totalCount, Integer isMore, Integer totalPage, Integer startIndex, List<T> items, String orderProperty, String orderDirection) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.isMore = isMore;
        this.totalPage = totalPage;
        this.startIndex = startIndex;
        this.items = items;
        this.orderProperty = orderProperty;
        this.orderDirection = orderDirection;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public String getOrderDirection() { return orderDirection; }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
}