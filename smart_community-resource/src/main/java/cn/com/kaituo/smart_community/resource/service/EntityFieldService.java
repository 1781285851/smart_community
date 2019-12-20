package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceEntityField;
import cn.com.kaituo.smart_community.resource.model.repo.EntityFieldRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class EntityFieldService {

    @Autowired
    private EntityFieldRepository entityFieldRepository;

    public ResourceEntityField save(ResourceEntityField resourceEntityField){
        return entityFieldRepository.save(resourceEntityField);
    }

    public void delete(Long id){
        entityFieldRepository.deleteById(id);
    }

    public ResourceEntityField getOne(Long id){
        return entityFieldRepository.getOne(id);
    }

    public Page<ResourceEntityField> getAll(Pageable pageable){
        return entityFieldRepository.findAll(pageable);
    }
}
