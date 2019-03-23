package com.wuye.wuyehoutai2.baoxiu;


import com.wuye.wuyehoutai2.api.ResultApi;
import com.wuye.wuyehoutai2.api.ResultApiUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
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
        bean.setBaoxiuType(0);

        BaoXiuBean result = repository.save(bean);
        return ResultApiUtil.success(result);
    }


    //报修状态改变
    @PostMapping("/baoxiuChange")
    public ResultApi change(
            @RequestBody @Valid BaoXiuTypeForm baoxiuForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确，baoxiuForm={}", baoxiuForm);
            return ResultApiUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        BaoXiuBean bean = repository.findByBaoxiuId(baoxiuForm.getBaoxiuId());
        //0待维修，1已接单，2已处理待支付，3已支付待评价，4已完成
        if (baoxiuForm.getBaoxiuType() == 4 && StringUtils.isEmpty(baoxiuForm.getComment())) {
            return ResultApiUtil.error("评价内容不能为空");
        }

        bean.setBaoxiuType(baoxiuForm.getBaoxiuType());
        bean.setComment(baoxiuForm.getComment());
        BaoXiuBean save = repository.save(bean);
        return ResultApiUtil.success(save);
    }

    //报修list
    @PostMapping("/baoxiuList")
    public ResultApi list(@RequestParam("baoxiuType") Integer baoxiuType) {
        //查询条件构造
        Specification<BaoXiuBean> spec = (Specification<BaoXiuBean>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            try {
                list.add(cb.equal(root.get("baoxiuType").as(Integer.class), baoxiuType));
            } catch (Exception ignored) {
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        List<BaoXiuBean> list = repository.findAll(spec);
        return ResultApiUtil.success(list);
    }


}
