/*
 * *************************************************************************************
 *
 *   Project:        XXX
 *
 *   Copyright ©     2017-2020 HOMEII Co.,Ltd
 *                   All rights reserved.
 *
 *   This software is supplied only under the terms of a license agreement,
 *   nondisclosure agreement or other written agreement with HOMEII
 *   Co.,Ltd. Use, redistribution or other disclosure of any parts of this
 *   software is prohibited except in accordance with the terms of such written
 *   agreement with Banma Technologies Co.,Ltd. This software is confidential
 *   and proprietary information of HOMEII Co.,Ltd.
 *
 * *************************************************************************************
 *
 *   Class Name: com.inforsw.smarteq.common.global.ResponseResult
 *
 *   General Description:
 *
 *   Revision History:
 *                            Modification
 *    Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 *    *********************   ************    **********     *****************************
 *    Administrator                   2017-10-07
 *
 * **************************************************************************************
 */

package cn.com.kaituo.smart_community.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseResult implements Serializable{

	private static final long serialVersionUID = 4836516488186911004L;
	//状态码
	private String status;
	//返回的语句
	private String message;
	//存放返回的具体结果
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public ResponseResult(){

	}

	public String getStatus() {
		return status;
	}

	public ResponseResult setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	public void addObject(String key, Object value){
		resultMap.put(key, value);
	}
	

	
	public Object getObject(String key) {
		return resultMap.get(key);
	}
	

	
	public Map<String, Object> getModelMap(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		model.put("message", message);

		for (String key : resultMap.keySet()) {
			Object value = resultMap.get(key);
			model.put(key, value);
		}
		
		return model;
	}
}
