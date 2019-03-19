package com.wuye.wuyehoutai2.baoxiu;


import com.wuye.wuyehoutai2.api.ResultApi;
import com.wuye.wuyehoutai2.api.ResultApiUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/wuye")
@Slf4j
public class BaoXiuController {
    @Autowired
    private BaoXiuRepository repository;


    /**
     * 用户注册
     */
    @PostMapping("/submit")
    public ResultApi create(
            @RequestBody @Valid BaoXiuForm baoxiuForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，baoxiuForm={}", baoxiuForm);
            return ResultApiUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }


        BaoXiuBean bean = new BaoXiuBean();

        bean.setName(baoxiuForm.getName());
        bean.setPhone(baoxiuForm.getPhone());
        bean.setAddress(baoxiuForm.getAddress());
        bean.setContent(baoxiuForm.getContent());
        bean.setBaixiuType(0);

        BaoXiuBean result = repository.save(bean);
        return ResultApiUtil.success(result);
    }


}
