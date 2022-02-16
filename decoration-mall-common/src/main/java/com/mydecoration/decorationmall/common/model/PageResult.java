package com.mydecoration.decorationmall.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author chaibing
 * @Description 分页查询封装类
 * @create 2022/2/16 14:24
 */
@Data
public class PageResult<T> implements Serializable {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 列表数据
     */
    private List<T> rows;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<T> list, Integer totalCount, Integer pageSize, Integer currPage) {
        if (list == null) {
            this.rows = Collections.EMPTY_LIST;
        } else {
            this.rows = list;
        }

        this.totalCount = totalCount == null ? 0 : totalCount;
        this.pageSize = pageSize == null ? 0 : pageSize;
        this.currPage = currPage == null ? 0 : currPage;
        this.totalPage = pageSize == null ? 0 : (int) Math.ceil((double) totalCount / pageSize);
    }

    public static <T> PageResult<T> getEmptyResult() {
        return new PageResult<T>(Collections.emptyList(), 0, null, null);
    }

    public static <T> PageResult<T> valueOf(List<T> list, Long totalCount, Integer pageSize, Integer currPage) {
        return new PageResult(list, totalCount.intValue(), pageSize, currPage);
    }
}
