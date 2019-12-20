package cn.com.kaituo.smart_community.gatesvr.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.kaituo.smart_community.common.http.CONSTS;
import cn.com.kaituo.smart_community.common.http.MessageCode;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.gatesvr.feign.LoginService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月9日        Congcong cc.         1.0            Initial Version

*/
@Log4j2
@Component
public class TokenProxy extends ZuulFilter{
	@Autowired
	private LoginService loginService;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public boolean shouldFilter() {
		log.info("即将判定是否需要验证 Token……");
		
        RequestContext ctx = RequestContext.getCurrentContext();
        //判断之前的过滤器是否执行成功
        Object preResult = ctx.get(ProxyConstants.ZUUL_PROXY_RESULT_KEY);
        if (preResult == null || preResult.toString().equals("true")) {
        	//用request.getRequestURI()获取的路径是原始请求的路径
        	//用ctx.get("requestURI")获取的路径是转发后（过滤器序号>5）的路径
        	HttpServletRequest request = ctx.getRequest();
        	//增加原始请求地址到请求中
        	ctx.addZuulRequestHeader(CONSTS.ORIGIN_USER_IP_ADDRESS, request.getRemoteAddr());
        	log.info("用户原始客户端地址：" +ctx.getZuulRequestHeaders().get(CONSTS.ORIGIN_USER_IP_ADDRESS));
            
            String URI = String.valueOf(ctx.get("requestURI"));
            log.info("即将请求资源URI：" +URI);
            if (!StringUtils.isEmpty(URI)) {
                // 用户登录认证不需要验证 Token
                if (URI.startsWith("/sso/login")
//                		||URI.startsWith("/sso/logout")
                	) {
                	log.info("登陆请求不需要验证 Token");
                    return false;
                }
            }
            return true;
        } else {
        	log.info("前置过滤器已标记 ZUUL_PROXY_RESULT_KEY =false");
            return false;
        }

    }
	
    /**
     * 过滤器的具体逻辑。
     */
    @Override
    public Object run() {
        log.info("进入验证Token方法");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token =request.getHeader("token"), originAddr =ctx.getZuulRequestHeaders().get(CONSTS.ORIGIN_USER_IP_ADDRESS);

        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        if (StringUtils.isEmpty(token)) {
            log.warn("Token 没有令牌，直接拒绝请求");
            setErrorResponse(MessageCode.COMMON_NO_TOKEN);
        }
        else {
            log.info("即将验证Token:" +token +"@" +originAddr);
            SoftworksResponse<Boolean> result =loginService.checkToken(token,originAddr);
            if (null ==request) {
                log.warn("Token 无此令牌或客户端网络环境不一致!");
                setErrorResponse(MessageCode.COMMON_TOKEN_INVALID);
            }
        }
        log.info("出验证Token方法");
        return null;
    }

    //所有的request，前缀处理过滤 PRE_DECORATION_FILTER_ORDER; //5
    //第一步验证签名 //8
    //第二步验证Token //9
    @Override
    public int filterOrder() {
        return 8;
    }

    /**
     * per：路由之前
     * routing：路由时
     * post：路由后
     * error：错误时调用
     */
    @Override
    public String filterType() {
        return "pre";
    }
    
    /**
     * filter过滤失败，直接返回错误信息 和 指定http 状态码
     *
     * @param code
     * @param status
     */
    protected void setErrorResponse(MessageCode code, HttpStatus status) {
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.setResponseStatusCode(status.value());
        try {
            ctx.setResponseBody(mapper.writeValueAsString(SoftworksResponse.failure(code)));
        } catch (JsonProcessingException e) {
            log.warn("write response error!", e);
            ctx.setResponseBody(SoftworksResponse.failure(code).toString());
        }
        ctx.getResponse().setContentType("text/html;charset=UTF-8");

        ctx.setSendZuulResponse(false);
        ctx.set(ProxyConstants.ZUUL_PROXY_RESULT_KEY, false);
    }

    /**
     * filter过滤失败，直接返回错误信息，http 状态码默认为200
     *
     * @param code
     */
    protected void setErrorResponse(MessageCode code) {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {
            ctx.setResponseBody(mapper.writeValueAsString(SoftworksResponse.failure(code)));
        } catch (JsonProcessingException e) {
            log.warn("write response error!", e);
            ctx.setResponseBody(SoftworksResponse.failure(code).toString());
        }
        ctx.getResponse().setContentType("text/html;charset=UTF-8");

        ctx.setSendZuulResponse(false);
        ctx.set(ProxyConstants.ZUUL_PROXY_RESULT_KEY, false);
    }
}
