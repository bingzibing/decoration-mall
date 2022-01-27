package com.mydecoration.decorationmall.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chaibing
 * @Description 自定义异常
 * @create 2022/1/26 15:18
 */
@Getter
@AllArgsConstructor
public class DecorationBizException {

    /**
     * 错误码
     */
    private final Integer errorCode;

    /**
     * 错误描述
     */
    private final String message;

    /**
     * 是否打印错误日志和堆栈
     */
    private final Boolean printError;

    public static DecorationBizException create(String message) {
        return new DecorationBizException(ErrorCodes.SYS_ERROR, message, Boolean.TRUE);
    }

    public static DecorationBizException create(Integer errorCode, String message) {
        return new DecorationBizException(errorCode, message, Boolean.TRUE);
    }

    public static DecorationBizException createNotPrint(String message) {
        return new DecorationBizException(ErrorCodes.SYS_ERROR, message, Boolean.FALSE);
    }

    public static DecorationBizException createNotPrint(Integer errorCode, String message) {
        return new DecorationBizException(errorCode, message, Boolean.FALSE);
    }
}
