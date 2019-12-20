package cn.com.kaituo.smart_community.produce.controller;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.produce.model.ProduceDeveloper;
import cn.com.kaituo.smart_community.produce.model.ProduceDever;
import cn.com.kaituo.smart_community.produce.service.DeverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@Api(tags ="开发机器人模板相关接口")
@RestController
@RequestMapping("/dever")
public class DeverController {

    @Autowired
    DeverService deverService;

    @GetMapping(value="/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取所有开发机器人模板",response = SoftworksResponse.class)
    public SoftworksResponse<List<ProduceDever>> list(){
        log.info("获取所有开发机器人模板");
        return SoftworksResponse.success(deverService.listValid());
    }

    @PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "注册开发机器人模板",response = SoftworksResponse.class)
    public SoftworksResponse<ProduceDever> generate(@RequestBody @ApiParam("开发机器人模板") ProduceDever produceDever){
        log.info("注册开发机器人模板");
        return SoftworksResponse.success(deverService.save(produceDever));
    }


    @PostMapping(value="/derive", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "派生开发成员",response = SoftworksResponse.class)
    public Callable<SoftworksResponse<ProduceDeveloper>> derive(@RequestParam @ApiParam("模板标识") Long deverId){
        log.info("派生开发成员");
        return () ->SoftworksResponse.success(deverService.derive(deverId));
    }

}
