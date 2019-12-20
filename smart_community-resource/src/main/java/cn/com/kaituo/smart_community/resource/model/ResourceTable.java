package cn.com.kaituo.smart_community.resource.model;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniqueDatabaseTable",columnNames = {"databaseId", "code","isDelete"})})
@EqualsAndHashCode(callSuper=true)
public class ResourceTable extends TimestampModel {
	private String name;
	private String code;
	private String originCode;
	private String comment;
	private String domain;
	private Timestamp lastExport;
	private Long databaseId =0l;
	@OneToMany(cascade ={CascadeType.ALL})
	@JoinColumn(name = "tableId",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private List<ResourceTableColumn> columns =new ArrayList<>();
}
