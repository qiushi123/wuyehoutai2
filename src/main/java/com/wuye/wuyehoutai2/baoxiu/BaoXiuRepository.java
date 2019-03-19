package com.wuye.wuyehoutai2.baoxiu;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户相关
 */
public interface BaoXiuRepository extends JpaRepository<BaoXiuBean, Long> {

    BaoXiuBean findByPhone(String phone);

}
