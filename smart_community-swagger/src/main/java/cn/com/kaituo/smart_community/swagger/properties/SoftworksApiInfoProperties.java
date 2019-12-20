package cn.com.kaituo.smart_community.swagger.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class SoftworksApiInfoProperties {
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private SoftworksContact contact;
    private String license;
    private String licenseUrl;
    private String version;
}
