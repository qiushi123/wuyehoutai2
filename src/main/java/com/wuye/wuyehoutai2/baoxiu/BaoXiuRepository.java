package com.wuye.wuyehoutai2.baoxiu;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户相关
 */
public interface BaoXiuRepository extends JpaRepository<BaoXiuBean, Long>, JpaSpecificationExecutor<BaoXiuBean> {

    BaoXiuBean findByPhone(String phone);

    BaoXiuBean findByBaoxiuId(Long id);
}
