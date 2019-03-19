package com.wuye.wuyehoutai2.login;

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
 * Created by qcl on 2018/7/10.
 * 管理员
 */
@Entity
@Data
@DynamicUpdate//自动更新时间
@EntityListeners(AuditingEntityListener.class)
public class UserBean {

    //员工和管理员信息相关
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;//主键

    private String userName;
    private String userPassword;
    private String userPhone;
    private String address;
    private Integer userType;//0普通业主，1物业管理员

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = Date2StringSerializer.class)//用于把date类型转换为string类型
    private Date createTime;//配送时间
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = Date2StringSerializer.class)//用于把date类型转换为string类型
    private Date updateTime;


}
