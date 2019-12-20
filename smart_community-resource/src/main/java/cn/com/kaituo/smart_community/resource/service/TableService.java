package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceTable;
import cn.com.kaituo.smart_community.resource.model.repo.TableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
@Service
@Log4j2
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Transactional
    public ResourceTable save(ResourceTable resourceTable){
        ResourceTable table =tableRepository.findResourceTableByCodeAndDatabaseId(
                resourceTable.getCode(),resourceTable.getDatabaseId());
        log.debug("查询是否存在重复的数据库表定义:" +(null !=table));
        if(null !=table) {
            log.info("数据库表已存在！逻辑删除重复的表定义: " +table.getId());
            tableRepository.logicDelete(table.getId());
        }
        return tableRepository.save(resourceTable);
    }

    @Transactional
    public boolean delete(Long id){
//        tableRepository.deleteById(id);
        tableRepository.logicDelete(id);
        return true;
    }

    public boolean export(Long id){
        tableRepository.export(id);
        //TODO：判断是否成功

        return true;
    }

    public ResourceTable getOne(Long id){
        return tableRepository.getOne(id);
    }

    public Page<ResourceTable> getAll(Pageable pageable){
        return tableRepository.findAll(pageable);
    }
}
