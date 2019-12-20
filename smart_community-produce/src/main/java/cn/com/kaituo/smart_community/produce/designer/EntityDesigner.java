package cn.com.kaituo.smart_community.produce.designer;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceTable;
import cn.com.kaituo.smart_community.produce.utils.EntityConverter;
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
public class EntityDesigner extends Designer<ResourceTable,ResourceEntity>{

    @Override
    public SystemLayer workLayer() {
        return SystemLayer.Entity;
    }

    @Override
    public String getName() {
        return "entityDesigner";
    }

    @Override
    public String getDescribe() {
        return "实体设计器";
    }

    @Override
    public ResourceEntity simpleDesigner(ResourceTable table) {
        log.debug("生成实体设计...");

        return EntityConverter.convertEntity(table);
    }
}
