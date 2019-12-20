package cn.com.kaituo.smart_community.produce;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

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
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@PropertySource(value ={"classpath:/database.properties", "classpath:/feign.properties", "classpath:/freemarker.properties"}, ignoreResourceNotFound = true)
public class ProduceApplication {
	public static void main(String[] args) {
		log.info("Coding API running...");
		SpringApplication.run(ProduceApplication.class, args);
	}
}
