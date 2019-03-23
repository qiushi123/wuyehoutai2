package com.wuye.wuyehoutai2.baoxiu;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by qcl on 2019/3/18
 * 微信：2501902696
 * desc: 改变维修订单状态
 */
@Data
public class BaoXiuTypeForm {
    @NotNull(message = "订单id不能为空")
    private Long baoxiuId;

    //订单状态值
    @NotNull(message = "订单状态不对")
    private Integer baoxiuType;

    private String comment;//评价内容
}
