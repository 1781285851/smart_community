package cn.com.kaituo.smart_community.resource.controller;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.resource.model.ResourceTableColumn;
import cn.com.kaituo.smart_community.resource.service.TableColumnService;
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
@RequestMapping("/resource/table")
public class TableColumnController {
	@Autowired
	TableColumnService tableColumnService;
	
	@PutMapping(value = "/column", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceTableColumn> save(@RequestBody @ApiParam("表字段定义") ResourceTableColumn column){
		log.info("保存表字段");
		return SoftworksResponse.success(tableColumnService.save(column));
	}

	@GetMapping(value="/column/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceTableColumn> detail(@PathVariable("id") Long id){
		log.info("获取表字段详细信息 id =" + id);
		return SoftworksResponse.success(tableColumnService.getOne(id));
	}

	@DeleteMapping(value="/column/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse delete(@PathVariable("id") Long id){
		log.info("删除表字段 id =" + id);
		tableColumnService.delete(id);

		return SoftworksResponse.success();
	}

	@GetMapping(value="/column/list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Page<ResourceTableColumn>> list(@PageableDefault Pageable pageable){
		log.info("获取表字段分页列表");

//		根据前台请求自定义分页
//		Pageable pageable_request = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "id"));
		return SoftworksResponse.success(tableColumnService.getAll(pageable));
	}
}