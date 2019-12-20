package cn.com.kaituo.smart_community.produce.feign;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.common.viewdata.project.Module;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
@FeignClient(value ="${environment}-${project}-${feign-project-name}-${feign-project-version}")
public interface FeignProjectService {
	// FeignClient调用 获取项目或模块信息
	@GetMapping(value = "/project/{id}/module", produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<Module> moduleDetail(@PathVariable("id") Long moduleId);
}