package cn.com.kaituo.smart_community.resource.model.repo;

import cn.com.kaituo.smart_community.resource.model.ResourceTable;
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
public interface TableRepository extends JpaRepository<ResourceTable, Long>, JpaSpecificationExecutor<ResourceTable>{

    /**
     * 记录最后导出时间
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ResourceTable t set t.lastExport=current_time where t.id =:id")
    void export(@Param(value = "id") Long id);

    /**
     * 标记为逻辑删除
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update ResourceTable t set t.isDelete=1,t.deleteTime=current_time where t.id =:id")
    void logicDelete(@Param(value = "id") Long id);

    /**
     * 查询重复的数据库表定义
     * @param code
     * @param dbid
     * @return
     */
    @Query("from ResourceTable t where t.isDelete =0 and t.code =:code and t.databaseId =:dbid")
    ResourceTable findResourceTableByCodeAndDatabaseId(@Param(value = "code") String code, @Param(value = "dbid") Long dbid);
}
