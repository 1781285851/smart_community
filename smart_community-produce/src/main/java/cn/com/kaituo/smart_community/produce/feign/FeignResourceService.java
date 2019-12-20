package cn.com.kaituo.smart_community.produce.feign;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.common.viewdata.resource.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月11日        Congcong cc.         1.0            Initial Version

*/
@Service
@FeignClient(value ="${environment}-${project}-${feign-resource-name}-${feign-resource-version}")
public interface FeignResourceService {
	// FeignClient调用 新增数据库表定义
	@PostMapping(value = "/resource/table", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceTable> saveTable(@RequestBody ResourceTable table);

	@GetMapping(value = "/resource/table/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceTable> tableDetail(@PathVariable("id") Long tableId);

	@PutMapping(value = "/resource/table/{id}/export", produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<Boolean> tableExport(@PathVariable("id") Long tableId);

	// FeignClient调用 新增实体定义
	@PostMapping(value = "/resource/entity", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceEntity> saveEntity(@RequestBody ResourceEntity entity);

	@GetMapping(value = "/resource/entity/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceEntity> entityDetail(@PathVariable("id") Long entityId);

	// FeignClient调用 新增实体定义
	@PostMapping(value = "/resource/repository", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceRepository> saveRepository(@RequestBody ResourceRepository repository);

	// FeignClient调用 新增实体定义
	@PostMapping(value = "/resource/service", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceService> saveService(@RequestBody ResourceService service);

	// FeignClient调用 新增实体定义
	@PostMapping(value = "/resource/controller", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<ResourceController> saveController(@RequestBody ResourceController controller);
}