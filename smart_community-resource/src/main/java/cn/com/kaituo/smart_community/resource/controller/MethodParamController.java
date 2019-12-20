package cn.com.kaituo.smart_community.resource.controller;

import cn.com.kaituo.smart_community.resource.service.MethodParamService;
import cn.com.kaituo.smart_community.common.http.MessageCode;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.resource.model.ResourceMethodParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年4月16日        Congcong cc.         1.0            Initial Version

*/
@RestController
@Api
@Log4j2
@RequestMapping("/resource/method")
public class MethodParamController {
	@Autowired
    MethodParamService methodParamService;
	
	@PostMapping(value = "/param", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceMethodParam> save(@RequestBody @ApiParam("方法参数定义") ResourceMethodParam param){
		log.info("保存方法参数定义");
		ResourceMethodParam result =null;
		try{
			result =methodParamService.save(param);
		}
		catch (Exception e){
			log.warn("保存方法参数定义失败！" +e.getMessage());
			e.printStackTrace();
			return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
		}
		return SoftworksResponse.success(result);
	}

	@GetMapping(value="/param/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceMethodParam> detail(@PathVariable("id") Long id){
		log.info("获取方法参数详细信息 id =" + id);
		return SoftworksResponse.success(methodParamService.getOne(id));
	}

	@DeleteMapping(value="/param/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse delete(@PathVariable("id") Long id){
		log.info("删除方法参数 id =" + id);
		methodParamService.delete(id);
		//TODO:实际业务中表采取逻辑删除的方案

		return SoftworksResponse.success();
	}

	@GetMapping(value="/param-list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Page<ResourceMethodParam>> list(@PageableDefault Pageable pageable){
		log.info("获取方法参数分页列表");

//		根据前台请求自定义分页
//		Pageable pageable_request = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "id"));
		return SoftworksResponse.success(methodParamService.getAll(pageable));
	}
}