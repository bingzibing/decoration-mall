package com.mydecoration.decorationmall.common.model;

import lombok.Data;

@Data
public class PageQuery implements ReadableQueryEntity {

    protected Integer pageIndex = 1;
    protected Integer pageSize = 50;
    protected Boolean page = Boolean.TRUE;

    public int getPageOffset() {
        return (pageIndex - 1) * pageSize;
    }

    public Integer getOffset() {
        return (pageIndex - 1) * pageSize;
    }

    public Integer getOffsetPage() {
        return pageIndex - 1;
    }
}
