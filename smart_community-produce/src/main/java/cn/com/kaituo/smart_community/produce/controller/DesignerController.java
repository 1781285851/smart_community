package cn.com.kaituo.smart_community.produce.controller;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.produce.service.DesignerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-24        Congcong cc.         1.0            Initial Version
 */
@Log4j2
@Api(tags ="系统设计器相关接口")
@RestController
@RequestMapping("/designer")
public class DesignerController {

    @Autowired
    DesignerService designerService;

    @PostMapping(value="/table", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "单表简单设计，异步接口",response = SoftworksResponse.class)
    public Callable<SoftworksResponse<Boolean>> generate(@RequestParam("tableId") @ApiParam("实体标识") Long tableId){
        log.info("开始生成单表相关设计");
        return () -> SoftworksResponse.success(designerService.simpleDesign(tableId));
    }
}
