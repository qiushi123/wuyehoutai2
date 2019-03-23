package com.wuye.wuyehoutai2.login;


import com.wuye.wuyehoutai2.api.ResultApi;
import com.wuye.wuyehoutai2.api.ResultApiUtil;
import com.wuye.wuyehoutai2.api.ResultEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/wuye")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository repository;


    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResultApi create(
            @RequestBody @Valid UserRegisterForm userForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，userForm={}", userForm);
            return ResultApiUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        UserBean userBean = repository.findByUserPhone(userForm.getUserPhone());
        if (userBean != null && !StringUtils.isEmpty(userBean.getUserPhone())) {
            log.error("[查询用户] 用户已注册过");
            return ResultApiUtil.error(ResultEnum.USER_HAVE_EXIST);
        }

        UserBean user = new UserBean();

        user.setUserName(userForm.getUserName());
        user.setUserPhone(userForm.getUserPhone());
        user.setUserPassword(userForm.getUserPassword());
        user.setAddress(userForm.getAddress());
        user.setUserType(0);//默认0普通用户

        UserBean result = repository.save(user);
        return ResultApiUtil.success(result);
    }

    /**
     * 用户地址修改
     */
    @PostMapping("/changeAddress")
    public ResultApi changeAddress(
            @RequestParam("userPhone") String userPhone,
            @RequestParam("address") String address) {
        if (StringUtils.isEmpty(userPhone)) {
            return ResultApiUtil.error(ResultEnum.USER_NO);
        }
        if (StringUtils.isEmpty(address)) {
            return ResultApiUtil.error("地址不能为空");
        }

        UserBean userBean = repository.findByUserPhone(userPhone);
        userBean.setAddress(address);

        UserBean result = repository.save(userBean);
        return ResultApiUtil.success(result);
    }


    /**
     * 查询某一个员工的信息
     */
    @PostMapping("/login")
    public ResultApi getStaffInfo(
            @RequestBody @Valid UserBeanForm userForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，userForm={}", userForm);
            return ResultApiUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        UserBean user = null;
        user = repository.findByUserPhone(userForm.getUserPhone());
        if (user == null || StringUtils.isEmpty(user.getUserPhone())) {
            log.error("[查询用户] 用户不存在");
            return ResultApiUtil.error(ResultEnum.USER_NO);
        }
        String userPassword2 = user.getUserPassword();
        if (!userPassword2.equals(userForm.getUserPassword())) {
            log.error("[查询用户] 密码错误");
            return ResultApiUtil.error(ResultEnum.USER_PASSWORD_ERROR);
        }
        return ResultApiUtil.success(user);
    }
    /**
     * 查询某一个员工的信息
     */
    //    @PostMapping("/login")
    //    public ResultApi getStaffInfo(
    //            @RequestBody UserBean userBean) {
    //        if (StringUtils.isEmpty(userBean.getUserPhone())) {
    //            log.error("[查询用户] 手机格式错误");
    //            return ResultApiUtil.error(ResultEnum.USER_PHONE_ERROR);
    //        }
    //
    //        UserBean user = null;
    //        user = repository.findByUserPhone(userBean.getUserPhone());
    //        if (user == null || StringUtils.isEmpty(user.getUserPhone())) {
    //            log.error("[查询用户] 用户不存在");
    //            return ResultApiUtil.error(ResultEnum.USER_NO);
    //        }
    //        String userPassword2 = user.getUserPassword();
    //        if (!userPassword2.equals(userBean.getUserPassword())) {
    //            log.error("[查询用户] 密码错误");
    //            return ResultApiUtil.error(ResultEnum.USER_PASSWORD_ERROR);
    //        }
    //        return ResultApiUtil.success(user);
    //    }


}
