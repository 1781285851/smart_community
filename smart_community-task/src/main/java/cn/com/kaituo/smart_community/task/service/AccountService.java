package cn.com.kaituo.smart_community.task.service;

import cn.com.kaituo.smart_community.task.model.Account;
import cn.com.kaituo.smart_community.task.model.repo.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月17日        Congcong cc.         1.0            Initial Version

*/
@Service
@Log4j2
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    /**
     * 管理员登录
     *
     * @param username 登录账号
     * @param password 登录密码
     * @return
     */
    public boolean login(String username, String password) {
        log.debug("进 管理员登录 方法，请求参数：[1]" + username + ",[2]" + password);
        try {
            Account account = accountRepository.findByUsernameAndPassword(username, password);
            if (account ==null) {
                log.warn("用户名或密码错误，查询不到用户");
                return false;
            } else {
            	log.info("用户登陆成功！");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("用户登录失败，相关异常信息：" + e.getMessage());
            return false;
        }
    }

}
