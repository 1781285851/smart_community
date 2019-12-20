package cn.com.kaituo.smart_community.login;

import java.util.HashMap;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月9日        Congcong cc.         1.0            Initial Version

* 存放登陆用户token……
*/
@Log4j2
public final class TokenHandler {
	public enum TokenStates{IsOk,Invalid,NetChanged}
	private static TokenHandler store =null;
	private HashMap<String,String> tokenMap =new HashMap<>();
	private TokenHandler(){};
	
	public static TokenHandler getInstance(){
		if(store ==null){
			store = new TokenHandler();
		}
		return store;
	}
	
	public String makeToken(String clientTab){
		String token =String.valueOf( UUID.randomUUID());
		tokenMap.put(token, clientTab);
		
		log.info("创建令牌（" +clientTab +"）：" +token);
		log.info("当前有效令牌数：" +tokenMap.size());
		return token;
	}
	
	public TokenStates checkToken(String token, String clientAddr){
		if(tokenMap.containsKey(token)){
			String clientStr =tokenMap.get(token);
//			log.info("令牌客户信息：" +clientStr.substring(clientStr.indexOf('@')));
			if(clientStr.substring(clientStr.indexOf('@') +1).equals(clientAddr)){
				log.info("令牌有效!");
				return TokenStates.IsOk;
			}
			else{
				log.warn("客户端网络环境不一致!" +token);
				return TokenStates.NetChanged;
			}
		}
		else{
			log.warn("无效令牌!" +token);
			return TokenStates.Invalid;
		}
	}

	public Boolean cleanToken(String token){//原来是void		MaoNian
		if(tokenMap.containsKey(token)){
			log.info("清除会话令牌：" +token);
			tokenMap.remove(token);
			return true;//MaoNian
		}
		else{
			log.warn("清除不存在的会话令牌：" +token);
			return false;//MaoNian
		}
	}
}
