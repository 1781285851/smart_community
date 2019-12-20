package cn.com.kaituo.smart_community.produce.model;

import cn.com.kaituo.smart_community.common.RandomNameGenerator;
import cn.com.kaituo.smart_community.common.RandomGenerator;
import cn.com.kaituo.smart_community.common.Skills;
import cn.com.kaituo.smart_community.common.SkillsConverter;
import cn.com.kaituo.smart_community.common.model.TimestampModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uniquePackageEntity",columnNames = {"code", "version"})})
@EqualsAndHashCode(callSuper=true)
@ApiModel("开发机器人模板")
public class ProduceDever extends TimestampModel {
	@ApiModelProperty("模板名称")
	private String name;
	@ApiModelProperty("模板代码")
	private String code;
	@ApiModelProperty("版本号")
	private Integer version;
	@ApiModelProperty("模板说明")
	private String notes;
	//只对标准工作量和持续工作模式有效
	@ApiModelProperty("效率百分值")
	private Integer ability =RandomGenerator.getRandom(50) +75;
	@ApiModelProperty("稳定性百分值")
	private Integer stability =RandomGenerator.getRandom(30) +75;
	@ApiModelProperty("支持的技能")
    @Convert(converter = SkillsConverter.class)
	private List<Skills> skills;

	public ProduceDeveloper derive(){
		ProduceDeveloper produceDeveloper =new ProduceDeveloper();
		produceDeveloper.setDeverId(id);
		String[] name = RandomNameGenerator.takeName();
		produceDeveloper.setPetName(name[0]);
		produceDeveloper.setGender(name[1]);
		produceDeveloper.setAbility(ability());
		produceDeveloper.setSkills(skills);
		return produceDeveloper;
	}

	/**
	 * 运算派生的成员工作能力
	 * @return
	 */
	Integer ability(){
		if(stability <100)
			return ability -RandomGenerator.getRandom(100 -stability);
		return ability;
	}

	public static void main(String[] arg){
		ProduceDever robot =new ProduceDever();
		System.out.println(robot.derive());
	}
}