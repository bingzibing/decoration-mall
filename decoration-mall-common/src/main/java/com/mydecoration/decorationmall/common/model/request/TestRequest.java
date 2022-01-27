package com.mydecoration.decorationmall.common.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chaibing
 * @Description 测试请求类
 * @create 2022/1/27 13:53
 */
@Data
public class TestRequest implements Serializable {

    private Long groupId;

    /**
     * 身份标识id
     */
    private Long identityId;

    /**
     * eg:"imei"、"miid"
     */
    private String type;

    /**
     * 标签值(多个值之间用空格隔开)
     */
    private String identityValues;
}
