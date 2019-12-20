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

*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("${tableRefer}")
@ApiModel(value = "${title}")
public class ${name}Entity{
	<@generateFields/>
}
<#macro generateFields>
<#list fields as property>
	@ApiModelProperty(value = "${property.title}")
	<#if property.name == "id">
	@TableId(value="id",type= IdType.AUTO)
	<#else>
	@TableField("${property.columnRefer}")
	</#if>
	private ${property.dataType} ${property.name};
</#list>
</#macro>