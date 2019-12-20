package cn.com.kaituo.smart_community.produce.designer;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceController;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
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
public class ControllerDesigner extends Designer<ResourceEntity, ResourceController>{

    @Override
    public SystemLayer workLayer() {
        return SystemLayer.Controller;
    }

    @Override
    public String getName() {
        return "controllerDesigner";
    }

    @Override
    public String getDescribe() {
        return "接口层设计器";
    }

    @Override
    public ResourceController simpleDesigner(ResourceEntity entity) {
        log.debug("生成接口层设计...");
        ResourceController controller =new ResourceController();
        controller.setName(entity.getOriginName() +"Controller");
        controller.getEntities().add(entity);

        return controller;
    }
}
