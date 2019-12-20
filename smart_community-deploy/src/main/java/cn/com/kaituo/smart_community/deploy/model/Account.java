package cn.com.kaituo.smart_community.deploy.model;

import cn.com.kaituo.smart_community.common.model.LogicDeleteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

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
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@Entity
@Table(name="t_auth_account")
public class Account extends LogicDeleteModel {

	private String username;
	private String realname;//昵称
	private String password;
	private Timestamp createTime;
	private Timestamp updateTime;
    
	public Account() {
		// TODO Auto-generated constructor stub
	}
}
