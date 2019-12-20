/**
 * 
 */
package cn.com.kaituo.smart_community.deploy.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
* Licensed to Homeii LTD. under the terms of the Homeii
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2019年7月9日        Congcong cc.         1.0            Initial Version

*/
@Data
@AllArgsConstructor
@ApiModel(value = "SuccessData", description = "登陆成功返回数据")
public class SuccessData {
	@ApiModelProperty(value = "会话令牌")
	private String token;
	@ApiModelProperty(value = "用户标识")
	private Long userId;
	@ApiModelProperty(value = "用户姓名")
	private String username;
}
