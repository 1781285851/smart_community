package cn.com.kaituo.smart_community.produce.designer;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceService;
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
public class ServiceDesigner extends Designer<ResourceEntity, ResourceService>{

    @Override
    public SystemLayer workLayer() {
        return SystemLayer.Service;
    }

    @Override
    public String getName() {
        return "serviceDesigner";
    }

    @Override
    public String getDescribe() {
        return "业务层设计器";
    }

    @Override
    public ResourceService simpleDesigner(ResourceEntity entity) {
        log.debug("生成业务层设计...");
        ResourceService service =new ResourceService();
        service.setName(entity.getOriginName() +"Service");
        service.getEntities().add(entity);

        return service;
    }
}
