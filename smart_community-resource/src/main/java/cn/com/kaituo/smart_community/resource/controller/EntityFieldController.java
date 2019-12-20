package cn.com.kaituo.smart_community.resource.controller;

import cn.com.kaituo.smart_community.resource.service.EntityFieldService;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.resource.model.ResourceEntityField;
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
@RequestMapping("/resource/entity")
public class EntityFieldController {
	@Autowired
    EntityFieldService entityFieldService;
	
	@PutMapping(value = "/field", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceEntityField> save(@RequestBody @ApiParam("实体属性定义")ResourceEntityField field){
		log.info("保存资源实体属性");
		return SoftworksResponse.success(entityFieldService.save(field));
	}

	@GetMapping(value="/field/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceEntityField> detail(@PathVariable("id") Long id){
		log.info("获取资源实体属性详细信息 id =" + id);
		return SoftworksResponse.success(entityFieldService.getOne(id));
	}

	@DeleteMapping(value="/field/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse delete(@PathVariable("id") Long id){
		log.info("删除资源实体属性 id =" + id);
		entityFieldService.delete(id);

		return SoftworksResponse.success();
	}

	@GetMapping(value="/field-list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Page<ResourceEntityField>> list(@PageableDefault Pageable pageable){
		log.info("获取资源实体属性分页列表");

//		根据前台请求自定义分页
//		Pageable pageable_request = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "id"));
		return SoftworksResponse.success(entityFieldService.getAll(pageable));
	}
}

