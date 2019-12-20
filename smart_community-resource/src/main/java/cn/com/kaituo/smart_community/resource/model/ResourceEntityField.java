package cn.com.kaituo.smart_community.resource.model;

import cn.com.kaituo.smart_community.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月17日        Congcong cc.         1.0            Initial Version
* 实体属性定义，数据类型为java数据类型
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniqueEntityField",columnNames = {"entityId", "name"})})
@EqualsAndHashCode(callSuper=true)
public class ResourceEntityField extends BaseModel {
	private Long entityId;
	private String name;
	private String title;
	private String comment;
	private String dataType;
	private String columnRefer;
	private Integer isTransient =0;
}
