package cn.com.kaituo.smart_community.resource.model.repo;

import cn.com.kaituo.smart_community.resource.model.ResourceEntityField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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
public interface EntityFieldRepository extends JpaRepository<ResourceEntityField, Long>, JpaSpecificationExecutor<ResourceEntityField>{

}
