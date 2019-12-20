package cn.com.kaituo.smart_community.core;

import com.github.tobato.fastdfs.FdfsClientConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
@Configuration
@Import(value = {FdfsClientConfig.class})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FdfsCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(false)
                .maxAge(3600);
        log.info("CorsRegistry set...");
    }
}
