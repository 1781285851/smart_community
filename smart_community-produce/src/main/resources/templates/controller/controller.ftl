package ${javaPackage}.controller;

import ${javaPackage}.model.${name}Entity;
import ${javaPackage}.service.${name}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j;
import java.util.List;

/**
* Licensed to Homeii LTD. under the terms of the Homeii
* Software License version 1.0.
*
* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* ${.now?string("yyyy-MM-dd")}        Congcong cc.         1.0            Initial Version

*/
@RestController
@Api(tags = "${title!'对象'}相关接口")
@Log4j
@RequestMapping("/${tableRefer}")
public class ${name}Controller {
	@Autowired
	${name}Service service;

	@ApiOperation(value = "新增${title!'对象'}",response = SoftworksResponse.class)
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean> save(@RequestBody @ApiParam("实体定义") ${name}Entity entity){
		log.info("保存${title!'对象'}");
	Boolean result =service.save(entity);
	if(result)
		return SoftworksResponse.success(result);
	return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
	}

	@ApiOperation(value = "修改${title!'对象'}",response = SoftworksResponse.class)
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean> update(@RequestBody @ApiParam("实体定义") ${name}Entity entity){
		log.info("修改${title!'对象'}");
		Boolean result =service.update(entity);
		if(result)
			return SoftworksResponse.success(result);
		return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
	}

	@ApiOperation(value = "查看${title!'对象'}详情",response = SoftworksResponse.class)
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<${name}Entity> detail(@PathVariable("id") Long id){
		log.info("获取${title!'对象'}详细信息 id =" + id);
		return SoftworksResponse.success(service.getOne(id));
	}

	@ApiOperation(value = "删除${title!'对象'}",response = SoftworksResponse.class)
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse delete(@PathVariable("id") Long id){
		log.info("删除${title!'对象'} id =" + id);
		Boolean result =service.delete(id);
		if(result)
			return SoftworksResponse.success(result);
		return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
	}

	@ApiOperation(value = "获取${title!'对象'}列表",response = SoftworksResponse.class)
	@GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<List<${name}Entity>> list(){
		log.info("获取${title!'对象'}列表");

		//根据前台请求自定义分页
		//Pageable pageable_request = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "id"));
		return SoftworksResponse.success(service.getAll());
	}
}