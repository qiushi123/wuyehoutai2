package com.wuye.wuyehoutai2.baoxiu;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Created by qcl on 2019/3/18
 * 微信：2501902696
 * desc:
 */
@Data
public class BaoXiuForm {
    @NotEmpty(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须是11位")
    private String phone;

    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "报修内容不能为空")
    private String content;
}
