package com.mydecoration.decorationmall.common.model.request;

import lombok.Data;

import java.io.Serializable;

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
}
