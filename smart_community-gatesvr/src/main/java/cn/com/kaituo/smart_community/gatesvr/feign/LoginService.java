package cn.com.kaituo.smart_community.gatesvr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import cn.com.kaituo.smart_community.common.http.CONSTS;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;

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
@FeignClient(value ="${environment}-${project}-${feign-login-name}-${feign-login-version}")	//配置login的负载均衡
public interface LoginService {
	// FeignClient调用 检查 Token 有效性
	@GetMapping(value = "/sso/check-token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SoftworksResponse<Boolean> checkToken(@RequestParam("token") String token, @RequestHeader(CONSTS.ORIGIN_USER_IP_ADDRESS) String originAddr);
}