/**
 * 
 */
package cn.com.kaituo.smart_community.task.controller.vo;

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
@ApiModel(value = "LoginData", description = "登陆数据")
public class LoginData {
	@ApiModelProperty(value = "用户姓名", required = false)
	private String username;
	@ApiModelProperty(value = "用户密码（MD5）", required = false)
	private String password;

}
