package com.example.notebook.exception;


import lombok.Getter;

/**
 * 自定义常见异常
 */
public class CommonException extends Exception {

    private static final Integer CODE = 500;

    private static final String MESSAGE = "服务器内部错误";

    @Getter
    private Integer code;

    @Getter
    private String message;

    public CommonException() {
        this.code = CODE;
        this.message = MESSAGE;
    }
}
