package cn.com.kaituo.smart_community.produce.controller;

import com.alibaba.fastjson.JSONObject;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.produce.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags ="代码生成器相关接口")
@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    GeneratorService generatorService;

    @GetMapping(value="/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取所有注册的代码生成器",response = SoftworksResponse.class)
    public SoftworksResponse<JSONObject> generate(){
        log.info("获取所有注册的代码生成器");
        return SoftworksResponse.success(generatorService.generators());
    }

    @PostMapping(value="/load-table-from-pdm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "数据库表定义文件（PDM）上传并导入，异步接口",response = SoftworksResponse.class)
    public Callable<SoftworksResponse<Boolean>> load(MultipartFile file){
        log.info("即将从PDM定义文件中导入数据库表");
        return () -> SoftworksResponse.success(generatorService.load(file));
    }

    @PostMapping(value="/{moduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据数据库表定义生成CURD代码，异步接口",response = SoftworksResponse.class)
    public Callable<SoftworksResponse<Boolean>> generate(@PathVariable  @ApiParam("模块标识") Long moduleId, @RequestParam @ApiParam("数据库表标识") Long tableId){
        log.info("即将在指定模块中生成数据库表相关的源代码");
        return () -> SoftworksResponse.success(generatorService.generate(moduleId, tableId));
    }
}
