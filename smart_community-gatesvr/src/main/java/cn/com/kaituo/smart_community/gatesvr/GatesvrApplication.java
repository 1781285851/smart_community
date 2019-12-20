package cn.com.kaituo.smart_community.gatesvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;

import lombok.extern.log4j.Log4j2;

/**
* Licensed to HOMEII,Inc. under the terms of the HOMEII
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2017年10月12日        Congcong cc.         1.0            Initial Version

*/
@Log4j2
@EnableZuulProxy	//路由
@EnableDiscoveryClient	//让注册中心能够发现，扫描到该服务。@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以用于其他注册中心。
@EnableFeignClients	//feign负载均衡
@PropertySource(value = {"classpath:/feign.properties"}, ignoreResourceNotFound = true)	//加载这个配置文件
@SpringBootApplication	//主启动类
public class GatesvrApplication  {
	public static void main(String[] args) {
		log.info("Gatesvr zuul service running...");
		SpringApplication.run(GatesvrApplication.class, args);
	}
}
