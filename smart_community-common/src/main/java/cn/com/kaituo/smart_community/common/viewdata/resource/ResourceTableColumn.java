package cn.com.kaituo.smart_community.common.viewdata.resource;

import cn.com.kaituo.smart_community.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* Licensed to Homeii LTD. under the terms of the Homeii 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月17日        Congcong cc.         1.0            Initial Version
* 表字段定义，数据类型为列数据类型
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ApiModel("数据库表字段定义")
public class ResourceTableColumn extends BaseModel {
	@ApiModelProperty("所属表标识")
	private Long tableId;
	@ApiModelProperty("实际代码")
	private String code;
	@ApiModelProperty("设计代码")
	private String OriginCode;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("说明")
	private String comment;
	@ApiModelProperty("数据类型")
	private String dataType;
	@ApiModelProperty("数据长度")
	private Integer columnLength;
	@ApiModelProperty("字段序次")
	private Integer num;
	@ApiModelProperty("最后修改时间戳（秒）")
	private Long lastModify;
}
