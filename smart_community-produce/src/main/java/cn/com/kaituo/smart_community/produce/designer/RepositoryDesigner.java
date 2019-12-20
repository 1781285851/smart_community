package cn.com.kaituo.smart_community.produce.designer;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-30        Congcong cc.         1.0            Initial Version
 */
@Log4j2
public class RepositoryDesigner extends Designer<ResourceEntity, ResourceRepository>{

    @Override
    public SystemLayer workLayer() {
        return SystemLayer.Repository;
    }

    @Override
    public String getName() {
        return "repositoryDesigner";
    }

    @Override
    public String getDescribe() {
        return "持久层设计器";
    }

    @Override
    public ResourceRepository simpleDesigner(ResourceEntity entity) {
        log.debug("生成持久层设计...");
        ResourceRepository repository =new ResourceRepository();
        repository.setName(entity.getOriginName() +"Repository");
        repository.setBindEntity(entity);

        return repository;
    }
}
