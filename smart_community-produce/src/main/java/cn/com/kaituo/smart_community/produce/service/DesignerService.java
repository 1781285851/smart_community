package cn.com.kaituo.smart_community.produce.service;

import cn.com.kaituo.smart_community.common.viewdata.resource.*;
import cn.com.kaituo.smart_community.produce.designer.ControllerDesigner;
import cn.com.kaituo.smart_community.produce.designer.EntityDesigner;
import cn.com.kaituo.smart_community.produce.designer.RepositoryDesigner;
import cn.com.kaituo.smart_community.produce.designer.ServiceDesigner;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.produce.feign.FeignResourceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DesignerService {
    @Autowired
    FeignResourceService feignResourceService;

    /**
     *调用单实例设计器生成实体和持久层、业务层、接口层代码
     * @param tableId
     * @return
     */
    @Transactional
    public Boolean simpleDesign(Long tableId) {
        SoftworksResponse<ResourceTable> result = feignResourceService.tableDetail(tableId);
        ResourceTable table =result.getResult();
        log.debug("即将生成单表设计对象，参考表名：" +table.getName());

        ResourceEntity entity =new EntityDesigner().simpleDesigner(table);
        //采用保存后的实体继续后续设计
        entity =feignResourceService.saveEntity(entity).getResult();
        log.debug("完成实体设计：" +entity.getName());

        ResourceRepository repository =new RepositoryDesigner().simpleDesigner(entity);
        feignResourceService.saveRepository(repository);
        log.debug("完成持久层设计：" +repository.getName());

        ResourceService service =new ServiceDesigner().simpleDesigner(entity);
        feignResourceService.saveService(service);
        log.debug("完成业务层设计：" +service.getName());

        ResourceController controller =new ControllerDesigner().simpleDesigner(entity);
        feignResourceService.saveController(controller);
        log.debug("完成接口层设计：" +controller.getName());
        return true;
    }
}