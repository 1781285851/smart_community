package cn.com.kaituo.smart_community.login.controller;

import cn.com.kaituo.smart_community.core.http.CONSTS;
import cn.com.kaituo.smart_community.core.http.MessageCode;
import cn.com.kaituo.smart_community.core.http.SoftworksResponse;
import cn.com.kaituo.smart_community.login.TokenHandler;
import cn.com.kaituo.smart_community.login.controller.viewdata.LoginData;
import cn.com.kaituo.smart_community.login.controller.viewdata.SuccessData;
import cn.com.kaituo.smart_community.login.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

@Api
@Log4j2
@Controller
@RequestMapping("/sso")
public class LoginController {
	@Autowired
	AccountService accountService;

	@ApiOperation(value = "处理用户登录请求")
	@PostMapping(value="/login",produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody
	SoftworksResponse<SuccessData> login(@RequestBody LoginData data, @RequestHeader(CONSTS.ORIGIN_USER_IP_ADDRESS) String originAddr){
		String user =data.getUsername(), pwd =data.getPassword();
		log.info("登陆验证，用户名：" +user +"，密码（MD5）：" +pwd +"，客户端IP：" +originAddr);

		SuccessData successData =accountService.login(user, pwd);
		if(successData !=null) {
			//获取客户端IP地址，用于识别用户是否从一个设备登陆
			String clientTab =successData.getUserId() +"@" +originAddr;
			successData.setToken(TokenHandler.getInstance().makeToken(clientTab));
			return SoftworksResponse.success(successData);
		}
		else
			return SoftworksResponse.failure(MessageCode.COMMON_USER_LOGIN_FAIL);
	}

	@ApiOperation(value = "校验会话令牌")
	@GetMapping(value="/check-token", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean>  checkToken(@RequestParam String token, @RequestHeader(CONSTS.ORIGIN_USER_IP_ADDRESS) String originAddr){
		log.info("令牌验证，Token：" +token +"，客户端IP：" +originAddr);
        switch (TokenHandler.getInstance().checkToken(token, originAddr)){
			case IsOk:
				log.warn("Token 有效令牌!");
				return SoftworksResponse.success(Boolean.TRUE);
			case Invalid:
				log.warn("Token 无此令牌!");
				break;
			case NetChanged:
				log.warn("Token 客户端网络环境不一致!");
		}
		return SoftworksResponse.failure(MessageCode.COMMON_TOKEN_INVALID);
	}


	@ApiOperation(value = "处理用户退出")
	@DeleteMapping(value="/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody SoftworksResponse<Boolean>  logout(@RequestHeader(CONSTS.USER_TOKEN_KEY) String token){
/*		log.info("退出登陆：" +CONSTS.USER_TOKEN_KEY +" =" +token);
		TokenHandler.getInstance().cleanToken(token);
		return SoftworksResponse.success(Boolean.TRUE);*/
		log.info("退出登陆：" +CONSTS.USER_TOKEN_KEY +" =" +token);
		Boolean f = TokenHandler.getInstance().cleanToken(token);
		if (f==true)
			return SoftworksResponse.success(Boolean.TRUE);
		//当这个Token不存在或者已经退出过，则显示Token验证失败
		return SoftworksResponse.failure(MessageCode.COMMON_NO_TOKEN);
	}
}

