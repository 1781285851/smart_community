package cn.com.kaituo.smart_community.project.model;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-28        Congcong cc.         1.0            Initial Version
 * 项目和模块对应于数据库中同一张表，为了解决自身引用循环的问题，映射成两个不同的实体对象。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(value = "模块信息")
@Table(name = "project")
public class Module extends TimestampModel {
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
    @ApiModelProperty(value = "开始时间",dataType = "string", example ="2019-09-28")
    private Timestamp beginDate;
    @ApiModelProperty(value = "计划结束时间",dataType = "string", example ="2019-09-28")
    private Timestamp expectedEndDate;
    @ApiModelProperty(value = "实际结束时间",dataType = "string", example ="2019-09-28")
    private Timestamp actualEndDate;
    @ManyToOne
    @JoinColumn(name ="projectId",insertable =false,updatable =false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ApiModelProperty(value = "所属项目信息")
    private Module project;
}
