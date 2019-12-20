package cn.com.kaituo.smart_community.resource.model;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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
@MappedSuperclass
@EqualsAndHashCode(callSuper=true)
public class ResourceActClass extends TimestampModel {
	private String name;
	private String title;
	private String comment;
	private String javaPackage;
	@OneToMany(cascade ={CascadeType.ALL})
	@JoinColumn(name = "ownerId",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private List<ResourceMethod> methods;
}
