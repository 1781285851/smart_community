package cn.com.kaituo.smart_community.deploy.model.repo;

import cn.com.kaituo.smart_community.deploy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月17日        Congcong cc.         1.0            Initial Version

*/
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>{
    @Query("from Account a where a.username = :username and a.isDelete = 0")
    Account findByUserame(@Param(value ="username") String username);

    /**
     * 管理员的登录
     *
     * @param username 登录账号
     * @param password 登录密码
     * @return
     */
    @Query("from Account a where a.username = :username and a.password = :password and isDelete = 0")
    Account findByUsernameAndPassword(@Param(value = "username") String username, @Param(value = "password") String password);

    /**
     * 修改管理员的密码
     *
     * @param id       要修改的管理员的id
     * @param password 新的密码
     */
    @Transactional
    @Modifying
    @Query("update Account a set a.password = :password where a.id = :id")
    int updateAccountPasswordById(@Param(value = "id") Long id, @Param(value = "password") String password);
}
