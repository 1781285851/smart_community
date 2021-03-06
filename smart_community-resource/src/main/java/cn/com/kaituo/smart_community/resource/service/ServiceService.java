package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceService;
import cn.com.kaituo.smart_community.resource.model.repo.ServiceRepository;
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
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Transactional
    public ResourceService save(ResourceService resourceService){ return serviceRepository.save(resourceService); }

    @Transactional
    public void delete(Long id){serviceRepository.deleteById(id);}

    public ResourceService getOne(Long id){
        return serviceRepository.getOne(id);
    }

    public Page<ResourceService> getAll(Pageable pageable){
        return serviceRepository.findAll(pageable);
    }
}
