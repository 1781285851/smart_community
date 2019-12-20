package cn.com.kaituo.smart_community.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

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
@MappedSuperclass
@EqualsAndHashCode(callSuper=true)
public class LogicDeleteModel extends BaseModel {
	@ApiModelProperty("删除标识")
    protected Integer isDelete =0;
}
