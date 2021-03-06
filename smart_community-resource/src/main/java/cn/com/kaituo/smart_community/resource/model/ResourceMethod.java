package cn.com.kaituo.smart_community.resource.model;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true)
public class ResourceMethod extends TimestampModel {
	private Long ownerId;
	private String name;
	private String title;
	private String comment;
	private String returnType;
	@OneToMany(cascade ={CascadeType.ALL})
	@JoinColumn(name = "methodId",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private List<ResourceMethodParam> params;
}
