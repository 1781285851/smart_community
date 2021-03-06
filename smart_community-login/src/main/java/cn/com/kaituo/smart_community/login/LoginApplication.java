package cn.com.kaituo.smart_community.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@PropertySource(value = {"classpath:/database.properties"}, ignoreResourceNotFound = true)
public class LoginApplication  {
	public static void main(String[] args) {
		log.info("Login API running...");
		SpringApplication.run(LoginApplication.class, args);
	}
}
