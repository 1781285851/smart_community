package cn.com.kaituo.smart_community.produce.controller;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.produce.model.ProduceDeveloper;
import cn.com.kaituo.smart_community.produce.service.DeveloperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(tags ="开发机器人相关接口")
@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    DeveloperService developerService;

    @GetMapping(value="/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取所有开发机器人",response = SoftworksResponse.class)
    public SoftworksResponse<List<ProduceDeveloper>> list(){
        log.info("获取所有开发机器人");
        return SoftworksResponse.success(developerService.listValid());
    }

    @PutMapping(value="/{id}/stop", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "停用开发机器人",response = SoftworksResponse.class)
    public SoftworksResponse<Boolean> generate(@PathVariable("id") @ApiParam("开发机器人标识") Long developerId){
        log.info("停用开发机器人" +developerId);
        return SoftworksResponse.success(developerService.stop(developerId));
    }
}
