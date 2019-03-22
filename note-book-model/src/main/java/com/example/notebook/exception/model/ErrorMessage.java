package com.example.notebook.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage<T> {

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回状态
     */
    private Boolean success;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

}
