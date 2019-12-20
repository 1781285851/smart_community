package cn.com.kaituo.smart_community.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import cn.com.kaituo.smart_community.swagger.properties.SoftworksApiInfoProperties;
import cn.com.kaituo.smart_community.swagger.properties.SoftworksDocketProperties;

/**
 * * Licensed to HOMEII,Inc. under the terms of the HOMEII
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 *   Date           Author               Version        Comments
 *  2017-10-23    zhengjun.jing           1.0            Initial Version

 */

@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties implements java.io.Serializable{
    private Boolean enable;
    private SoftworksApiInfoProperties apiInfo;
    private SoftworksDocketProperties docket;
}
