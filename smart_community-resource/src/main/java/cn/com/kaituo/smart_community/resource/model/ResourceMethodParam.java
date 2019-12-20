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

*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniqueMethodParam",columnNames = {"name", "methodId"})})
@EqualsAndHashCode(callSuper=true)
public class ResourceMethodParam extends BaseModel {
	private Long methodId;
	private String name;
	private String title;
	private String comment;
	private String dataType;
	private Integer listOrder;
}
