package cn.com.kaituo.smart_community.resource.service;

import cn.com.kaituo.smart_community.resource.model.ResourceTableColumn;
import cn.com.kaituo.smart_community.resource.model.repo.TableColumnRepository;
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
public class TableColumnService {

    @Autowired
    private TableColumnRepository tableColumnRepository;

    public ResourceTableColumn save(ResourceTableColumn resourceTableColumn){
        return tableColumnRepository.save(resourceTableColumn);
    }

    public void delete(Long id){
        tableColumnRepository.deleteById(id);
    }

    public ResourceTableColumn getOne(Long id){
        return tableColumnRepository.getOne(id);
    }

    public Page<ResourceTableColumn> getAll(Pageable pageable){
        return tableColumnRepository.findAll(pageable);
    }
}
