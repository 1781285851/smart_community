package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceMethodParam;
import cn.com.kaituo.smart_community.resource.model.repo.MethodParamRepository;
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
public class MethodParamService {

    @Autowired
    private MethodParamRepository methodParamRepository;

    @Transactional
    public ResourceMethodParam save(ResourceMethodParam resourceMethodParam){ return methodParamRepository.save(resourceMethodParam); }

    @Transactional
    public void delete(Long id){methodParamRepository.deleteById(id);}

    public ResourceMethodParam getOne(Long id){
        return methodParamRepository.getOne(id);
    }

    public Page<ResourceMethodParam> getAll(Pageable pageable){
        return methodParamRepository.findAll(pageable);
    }
}
