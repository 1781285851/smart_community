package cn.com.kaituo.smart_community.deploy.controller;

import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.deploy.service.AccountService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sso")
public class LoginController {
	@Autowired
    AccountService accountService;
	
	@RequestMapping(value="/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean>  logout(@RequestParam String token){
		log.info("退出登陆……");
//		TokenHandler.getInstance().cleanToken(token);
		return SoftworksResponse.success(Boolean.TRUE);
	}
}

