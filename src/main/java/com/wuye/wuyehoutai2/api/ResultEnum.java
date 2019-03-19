package com.wuye.wuyehoutai2.api;

import lombok.Getter;

/**
 * 接口返回的状态码
 * 把所有的状态码统一用枚举列出来
 */
@Getter
public enum ResultEnum {
    //请求参数相关
    PARAM_ERROR(1, "参数不正确"),
    RESULT_ERROR(-100, "未知错误"),
    RESULT_NO(-101, "没有这条结果"),
    RESULT_OK(100, "成功"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    CODE_NOT_EXIST(12, "验证码不存在"),

    //用户相关
    USER_PHONE_ERROR(21, "用户手机号不能为空"),
    USER_PASSWORD_ERROR(22, "密码错误"),
    USER_HAVE_EXIST(23, "用户已注册过"),
    USER_NO(24, "用户不存在"),

    USER_NO_LOGIN(44, "用户没有登陆"),
    USER_ADMIN_NO(45, "管理员不存在"),
    USER_NO_AUTHORITY(47, "没有操作权限"),
    USER_NO_SUPER_ADMIN(49, "您不是超级管理员"),
    PHONE_HAS_UESED(50, "伙伴手机号已经注册过，请更换手机号");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
