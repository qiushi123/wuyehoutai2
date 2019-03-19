package com.wuye.wuyehoutai2.api;

import lombok.Data;

/**
 * 返回给前端的接口json
 */
@Data
public class ResultApi<T> {
    private Integer code;
    private String msg;
    private T data;
}
