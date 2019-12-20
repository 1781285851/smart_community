package cn.com.kaituo.smart_community.produce.model.repo;

import cn.com.kaituo.smart_community.produce.model.ProduceDever;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
public interface DeverRepository extends JpaRepository<ProduceDever, Long>, JpaSpecificationExecutor<ProduceDever>{
    @Query("from ProduceDever d where isDelete =0")
    public List<ProduceDever> findValid();

    @Transactional
    @Modifying
    @Query("update ProduceDever d set d.isDelete =1 where d.id =:id")
    void stop(@Param(value = "id") Long id);
}
