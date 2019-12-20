package  ${javaPackage}.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;

/**
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* ${.now?string("yyyy-MM-dd")}        Congcong cc.         1.0            Initial Version

<#if comment??>
* ${comment}
</#if>
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("${tableRefer}")
@KeySequence("mybatis_squence")
@ApiModel(value = "${title}")
public class ${name}Entity{
	<@generateFields/>
}
<#macro generateFields>
<#list fields as property>
	<#if (property.comment)??>
	/**${property.comment} **/
	</#if>
	<#if property.dataType =="Timestamp">
	@ApiModelProperty(value ="${property.title}", dataType ="string", example ="${.now?string("yyyy-MM-dd")}")
	<#else>
	@ApiModelProperty(value = "${property.title}")
	</#if>
	<#if property.name == "id">
	@TableId(value="id",type= IdType.INPUT)
	private ${property.dataType} ${property.name};
	<#else>
	@TableField("${property.columnRefer}")
	private ${property.dataType} ${property.name};
	</#if>

</#list>
</#macro>