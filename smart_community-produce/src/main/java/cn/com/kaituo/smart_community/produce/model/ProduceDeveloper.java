package cn.com.kaituo.smart_community.produce.model;

import cn.com.kaituo.smart_community.common.Skills;
import cn.com.kaituo.smart_community.common.SkillsConverter;
import cn.com.kaituo.smart_community.common.model.TimestampModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniquePackageEntity",columnNames = {"jobNumber"})})
@EqualsAndHashCode(callSuper=true)
public class ProduceDeveloper extends TimestampModel {
	@ApiModelProperty("模板标识")
	private Long deverId;
	//系统随机给定
	@ApiModelProperty("昵称")
	private String petName;
	//系统规则运算
	@ApiModelProperty("工号")
	private Long jobNumber;
	//系统随机给定
	@ApiModelProperty("性别")
	private String gender;
	//根据模板效率和稳定性随机运算，取值应为模板效率的50%~150%
	@ApiModelProperty("效率百分值")
	private Integer ability;
	//标准工作量（工作时间持续运行，按设定完成工作量） 1
	//持续工作（按设定工作量持续运行） 2
	//疯狂模式（按实际运行效率持续运行） 0
	@ApiModelProperty("工作方式")
	private Integer workMode =1;
	@ApiModelProperty("支持的技能")
	@Convert(converter = SkillsConverter.class)
	private List<Skills> skills;

	ProduceDeveloper(){}
}
