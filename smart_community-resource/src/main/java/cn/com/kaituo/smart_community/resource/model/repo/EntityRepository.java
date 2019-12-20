package cn.com.kaituo.smart_community.resource.model.repo;

import cn.com.kaituo.smart_community.resource.model.ResourceEntity;
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
public interface EntityRepository extends JpaRepository<ResourceEntity, Long>, JpaSpecificationExecutor<ResourceEntity>{
    /**
     * 标记为逻辑删除
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ResourceEntity t set t.isDelete=1,t.deleteTime=current_time where t.id =:id")
    void logicDelete(@Param(value = "id") Long id);

    /**
     * 查询重复的数据库表定义
     * @param name
     * @param javaPackage
     * @return
     */
    @Query("from ResourceEntity t where t.isDelete =0 and t.name =:name and t.javaPackage =:javaPackage")
    ResourceEntity findResourceEntityByNameAndJavaPackage(@Param(value = "name") String name, @Param(value = "javaPackage") String javaPackage);
}
