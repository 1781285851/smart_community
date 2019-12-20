package cn.com.kaituo.smart_community.swagger.properties;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class SoftworksContact {
    private String name;
    private String url;
    private String email;
}
