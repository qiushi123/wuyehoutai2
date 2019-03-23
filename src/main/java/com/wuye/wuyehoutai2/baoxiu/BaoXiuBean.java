package com.wuye.wuyehoutai2.baoxiu;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wuye.wuyehoutai2.utils.Date2StringSerializer;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Created by qcl on 2019/3/19
 * 微信：2501902696
 * desc:
 */
@Entity
@Data
@DynamicUpdate//自动更新时间
@EntityListeners(AuditingEntityListener.class)
public class BaoXiuBean {
    //员工和管理员信息相关
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long baoxiuId;//主键

    private String name;
    private String phone;
    private String content;
    private String address;
    private Integer baoxiuType;//0待维修，1已接单，2已处理待支付，3已支付待评价，4已完成
    private String comment;//评价内容

    private int price;//维修费用

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = Date2StringSerializer.class)//用于把date类型转换为string类型
    private Date createTime;//配送时间
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = Date2StringSerializer.class)//用于把date类型转换为string类型
    private Date updateTime;

}
