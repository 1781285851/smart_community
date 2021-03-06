package cn.com.kaituo.smart_community.produce.designer;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceView;
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
public class ViewDesigner extends Designer<ResourceEntity, ResourceView>{

    @Override
    public SystemLayer workLayer() {
        return SystemLayer.View;
    }

    @Override
    public String getName() {
        return "viewDesigner";
    }

    @Override
    public String getDescribe() {
        return "展现层设计器";
    }

    @Override
    public ResourceView simpleDesigner(ResourceEntity entity) {
        log.debug("生成表现层设计...");
        //TODO:

        return null;
    }
}
