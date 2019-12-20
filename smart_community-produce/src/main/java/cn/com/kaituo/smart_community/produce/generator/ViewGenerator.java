package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.produce.designer.SystemLayer;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import lombok.extern.log4j.Log4j2;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019年7月17日        Congcong cc.         1.0            Initial Version

 */

@Log4j2
public abstract class ViewGenerator extends LayerGenerator{
    @Override
    SystemLayer workLayer() {return SystemLayer.View; }

    public abstract void sourceProduce(ResourceEntity entity, String baseFolder, String sourceFolder);
}
