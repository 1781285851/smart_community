package cn.com.kaituo.smart_community.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

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
public class TimestampModel extends LogicDeleteModel {
	@ApiModelProperty(value ="创建时间", dataType ="string", example ="2019-09-25")
    protected Timestamp createTime =new Timestamp(new Date().getTime());
	@ApiModelProperty(value ="更新时间", dataType ="string", example ="2019-09-25")
    protected Timestamp updateTime;
	@ApiModelProperty(value ="标记删除时间", dataType ="string", example ="2019-09-25")
    protected Timestamp deleteTime;
}
