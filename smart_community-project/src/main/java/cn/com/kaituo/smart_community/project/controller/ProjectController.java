package cn.com.kaituo.smart_community.project.controller;

import cn.com.kaituo.smart_community.project.model.Module;
import cn.com.kaituo.smart_community.common.http.MessageCode;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.project.model.Project;
import cn.com.kaituo.smart_community.project.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags ="项目相关接口")
@Log4j2
@RequestMapping("/project")
public class ProjectController {
	@Autowired
    ProjectService projectService;

	@ApiOperation(value = "新增或保存项目信息")
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Project> save(@RequestBody @ApiParam("项目") Project project){
		log.info("保存项目信息");
		Project result =null;
		try{
			result =projectService.save(project);
		}
		catch (Exception e){
			log.warn("保存项目信息失败！" +e.getMessage());
			e.printStackTrace();
			return SoftworksResponse.failure(MessageCode.COMMON_DATA_ERROR);
		}
		return SoftworksResponse.success(result);
	}

	@ApiOperation(value = "查看项目详细信息")
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Project> detail(@PathVariable("id") Long id){
		log.info("获取项目详细信息 id =" + id);
		return SoftworksResponse.success(projectService.detail(id));
	}

	@ApiOperation(value = "查看模块详细信息")
	@GetMapping(value="/{id}/module", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Module> moduleDetail(@PathVariable("id") Long id){
		log.info("获取模块详细信息 id =" + id);
		return SoftworksResponse.success(projectService.moduleDetail(id));
	}

	@ApiOperation(value = "删除项目")
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse delete(@PathVariable("id") Long id){
		log.info("删除项目 id =" + id);
		projectService.delete(id);
		//TODO:实际业务中表采取逻辑删除的方案

		return SoftworksResponse.success();
	}

	@ApiOperation(value = "获取项目分页列表")
	@GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Page<Project>> list(@PageableDefault Pageable pageable){
		log.info("获取项目分页列表");

		return SoftworksResponse.success(projectService.list(pageable));
	}
}

