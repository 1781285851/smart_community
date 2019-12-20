package cn.com.kaituo.smart_community.common.viewdata.resource;

import cn.com.kaituo.smart_community.common.model.TimestampModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@ApiModel("数据库表定义")
public class ResourceTable extends TimestampModel {
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("实际代码")
	private String code;
	@ApiModelProperty("设计代码")
	private String originCode;
	@ApiModelProperty("说明")
	private String comment;
	@ApiModelProperty("业务域")
	private String domain;
	@ApiModelProperty("最后修改时间戳（秒）")
	private Long lastModify;
	@ApiModelProperty("最后导出时间戳（秒）")
	private Long lastExport;
	@ApiModelProperty("所属数据库标识")
	private Long databaseId =0l;
	@ApiModelProperty("表字段")
	private List<ResourceTableColumn> columns =new ArrayList<>();
}
