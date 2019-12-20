package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceMethod;
import cn.com.kaituo.smart_community.resource.model.repo.MethodRepository;
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
public class MethodService {

    @Autowired
    private MethodRepository methodRepository;

    @Transactional
    public ResourceMethod save(ResourceMethod resourceMethod){ return methodRepository.save(resourceMethod); }

    @Transactional
    public void delete(Long id){methodRepository.deleteById(id);}

    public ResourceMethod getOne(Long id){
        return methodRepository.getOne(id);
    }

    public Page<ResourceMethod> getAll(Pageable pageable){
        return methodRepository.findAll(pageable);
    }
}
