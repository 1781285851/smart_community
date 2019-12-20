package cn.com.kaituo.smart_community.common.viewdata.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-28        Congcong cc.         1.0            Initial Version
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "模块信息")
public class Module {
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "内部编码")
    private String number;
    @ApiModelProperty(value = "开发路径")           //项目或模块在开发空间的相对路径
    private String workspace;
    @ApiModelProperty(value = "源码包路径")         //项目或模块源码起始包路径
    private String basePackage;
    @ApiModelProperty(value = "简要说明")
    private String notes;
    @ApiModelProperty(value = "所属项目")
    private Module project;

    public String getWorkspace(){
        if(null ==project)
            return workspace;
        return project.workspace + File.separator +workspace;
    }
}
