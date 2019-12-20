package cn.com.kaituo.smart_community.produce.generator;

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
public class ViewVueGenerator extends ViewGenerator{
    @Override
    String getName() {return "VueGenerator";}

    @Override
    String getDescribe() {
        return "表现层生成器，代码规范：VUE,Jquery,ajax,H5";
    }

    @Override
    String targetSuffix() { return ".vue"; }

    @Override
    void sourceProduce(FrameworkGenerator framework, ResourceEntity entity) {

    }

    @Override
    public void sourceProduce(ResourceEntity entity, String baseFolder, String sourceFolder) {
        log.debug("生成表现层代码" +entity.getName());
    }
}
