package cn.com.kaituo.smart_community.common.viewdata.resource;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
@EqualsAndHashCode(callSuper=true)
public class ResourceEntity extends TimestampModel {
	private String name;
	private String title;
	private String comment;
	private String tableRefer;
	private String javaPackage;
	private List<ResourceEntityField> fields =new ArrayList<>();

	public String getOriginName(){
		if(StringUtils.isNotBlank(name) && name.endsWith("Entity"))
			return name.substring(0,name.length() -6);
		return name;
	}
}
