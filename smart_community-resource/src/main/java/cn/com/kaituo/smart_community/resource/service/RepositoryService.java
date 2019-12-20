package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceRepository;
import cn.com.kaituo.smart_community.resource.model.repo.RepoRepository;
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
public class RepositoryService {

    @Autowired
    private RepoRepository repoRepository;

    @Transactional
    public ResourceRepository save(ResourceRepository resourceRepository){ return repoRepository.save(resourceRepository); }

    @Transactional
    public void delete(Long id){repoRepository.deleteById(id);}

    public ResourceRepository getOne(Long id){
        return repoRepository.getOne(id);
    }

    public Page<ResourceRepository> getAll(Pageable pageable){
        return repoRepository.findAll(pageable);
    }
}
