package ${Entity.classPackage}.vo;

import java.util.Date;
import javax.persistence.Entity;
import com.infor.commons.web.domain.BaseEntity;
import javax.persistence.Table;
import javax.persistence.Column;
import java.math.BigInteger;
import java.math.BigDecimal;

/**
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* ${.now?string("yyyy-MM-dd")}        Congcong cc.         1.0            Initial Version

*/

@Entity
@Table(name = "${Entity.tableName}")
public class ${Entity.className} extends BaseEntity{  
	<@generateFields/>
	<@generateProperties/>  
}

<#macro generateFields>
	<#list Entity.properties as property>
	<#if property.javaType == "Date">
	@Column(name = "${property.propertyColName}",columnDefinition = "DATE")
	private ${property.javaType} ${property.propertyName};
	<#elseif property.javaType == "blob">
	@Column(name = "${property.propertyColName}",columnDefinition = "LONGBLOB")
	private ${property.javaType} ${property.propertyName};
	<#else>
		<#if property.propertyName == "id">
		<#else>
		@Column(name = "${property.propertyColName}")
		private ${property.javaType} ${property.propertyName};
		</#if>
	</#if>
	</#list>
</#macro>

<#macro generateProperties>
	<#list Entity.properties as property>
		<#if property.propertyName == "id">
		<#else>
			public ${property.javaType} get${property.propertyNameUpper}() {
				return this.${property.propertyName};
			}
			public void set${property.propertyNameUpper}(${property.javaType} value) {
				this.${property.propertyName} = value;
			}
		</#if>
	</#list>
</#macro>



