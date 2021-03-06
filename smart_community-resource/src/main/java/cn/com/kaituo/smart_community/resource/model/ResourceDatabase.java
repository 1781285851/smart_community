package cn.com.kaituo.smart_community.resource.model;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
public class ResourceDatabase extends TimestampModel {
	private String code;
	private String name;
	private String comment;
	private String dbms;
	private String datasourceUrl;
	private String datasourceUsername;
	private String datasourcePassword;
	@OneToMany(cascade ={CascadeType.ALL})
	@JoinColumn(name = "databaseId",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private List<ResourceTable> tables =new ArrayList<>();
}
