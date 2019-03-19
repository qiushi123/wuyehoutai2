package com.wuye.wuyehoutai2.api;


/**
 * Created by qcl on 2018/3/14.
 * 自定义异常
 */

public class ResultException extends RuntimeException {
    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ResultException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
