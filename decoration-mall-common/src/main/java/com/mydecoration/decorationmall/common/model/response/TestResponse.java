package com.mydecoration.decorationmall.common.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chaibing
 * @Description 测试响应类
 * @create 2022/1/27 13:55
 */
@Data
public class TestResponse implements Serializable {
    private static final long serialVersionUID = -7114542134649696797L;
    /**
     * 计划任务id
     */
    private Long planTaskId;

}
