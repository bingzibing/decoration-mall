package com.mydecoration.decorationmall.common.http;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final String SUCCESS_MESSAGE = "ok";
    private static final long serialVersionUID = -8156791249375065606L;
    private final int code;
    private final String message;
    private final T data;

    Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result(GeneralCodes.OK, SUCCESS_MESSAGE, data);
    }

    public static <T> Result<T> success(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result(500, message, null);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result(500, message, data);
    }

    public String toString() {
        return String.format("Result{code=%d, message=%s, data=%s}", this.code, this.message, this.data);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}
