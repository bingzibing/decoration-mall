package com.mydecoration.decorationmall.sdk.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    T data;
}
