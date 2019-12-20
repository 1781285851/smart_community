package cn.com.kaituo.smart_community.resource.model;

import lombok.*;

import javax.persistence.*;

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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniquePackageEntity",columnNames = {"name", "javaPackage"})})
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper=true)
public class ResourceRepository extends ResourceActClass {
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="entityId",foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private ResourceEntity bindEntity;
}
