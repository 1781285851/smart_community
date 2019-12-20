package cn.com.kaituo.smart_community.resource.controller;

import cn.com.kaituo.smart_community.common.http.MessageCode;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.resource.model.ResourceTable;
import cn.com.kaituo.smart_community.resource.service.TableService;
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
@RequestMapping("/resource")
public class TableController {
	@Autowired
	TableService tableService;
	
	@PostMapping(value = "/table", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceTable> save(@RequestBody @ApiParam("数据库表定义") ResourceTable table){
		log.info("保存数据库表定义");
		ResourceTable result =null;
		try{
			result =tableService.save(table);
		}
		catch (Exception e){
			log.warn("保存数据库表定义失败！" +e.getMessage());
			e.printStackTrace();
			return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
		}
		return SoftworksResponse.success(result);
	}

	@PutMapping(value="/table/{id}/export", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean> export(@PathVariable("id") Long tableId){
		log.info("获取数据库表详细信息 id =" + tableId);
		return SoftworksResponse.success(tableService.export(tableId));
	}

	@GetMapping(value="/table/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<ResourceTable> detail(@PathVariable("id") Long id){
		log.info("获取数据库表详细信息 id =" + id);
		return SoftworksResponse.success(tableService.getOne(id));
	}

	@DeleteMapping(value="/table/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean> delete(@PathVariable("id") Long id){
		log.info("删除数据库表 id =" + id);
		Boolean result =tableService.delete(id);
		//TODO:实际业务中表采取逻辑删除的方案

		return SoftworksResponse.success(result);
	}

	@GetMapping(value="/table-list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Page<ResourceTable>> list(@PageableDefault Pageable pageable){
		log.info("获取数据库表分页列表");

//		根据前台请求自定义分页
//		Pageable pageable_request = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "id"));
		return SoftworksResponse.success(tableService.getAll(pageable));
	}
}