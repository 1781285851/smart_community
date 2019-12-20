package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceEntity;
import cn.com.kaituo.smart_community.resource.model.repo.EntityRepository;
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
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    @Transactional
    public ResourceEntity save(ResourceEntity resourceEntity){
        ResourceEntity entity =entityRepository.findResourceEntityByNameAndJavaPackage(
                resourceEntity.getName(),resourceEntity.getJavaPackage());
        log.debug("查询是否存在重复的实体定义: " +(null !=entity));
        if(null !=entity){
            log.info("实体已存在，逻辑删除重复的实体定义！");
            entityRepository.logicDelete(entity.getId());
        }
        return entityRepository.save(resourceEntity);
    }

    /**
     * 逻辑删除实体定义
     * @param id
     */
    @Transactional
    public void delete(Long id){
//        entityRepository.deleteById(id);
        entityRepository.logicDelete(id);
    }

    public ResourceEntity getOne(Long id){
        return entityRepository.getOne(id);
    }

    public Page<ResourceEntity> getAll(Pageable pageable){
        return entityRepository.findAll(pageable);
    }
}
