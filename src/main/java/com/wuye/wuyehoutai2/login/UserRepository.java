package com.wuye.wuyehoutai2.login;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户相关
 */
public interface UserRepository extends JpaRepository<UserBean, Long> {

    UserBean findByUserPhone(String phone);

}
