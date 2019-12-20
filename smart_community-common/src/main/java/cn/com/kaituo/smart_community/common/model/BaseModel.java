package cn.com.kaituo.smart_community.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@MappedSuperclass	//这个注解表示在父类上面的，用来标识父类
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class BaseModel {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
}
