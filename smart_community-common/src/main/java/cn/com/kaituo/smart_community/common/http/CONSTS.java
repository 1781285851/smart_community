/**
 * 常量类：用于定义系统中引用的常量。如，公用变量名等
 */
package cn.com.kaituo.smart_community.common.http;

/**
* Licensed to HOMEII,Inc. under the terms of the HOMEII 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* 2017年12月14日        Congcong cc.         1.0            Initial Version

*/

public class CONSTS {
//	如果作为RequestHeader变量，不要大小写混写，zuul头变量默认小写
	static public final String SqlLikeChar ="%";
	static public final String UserSerial_Name ="serial";
	static public final String USER_TOKEN_KEY = "token";
    /** zuul filter 过滤链装配 原生客户端IP地址头属性名称 */
    public static final String ORIGIN_USER_IP_ADDRESS= "origin-addr";
    
	static public final String GofunToken_Name ="Authorization";
	static public final String GofunCorp_Serial ="1001";
	static public final String GofunPayType_Value ="8";
	static public final String GofunSourceFor_Value ="jinqiao";
	
	static public final String BusService_Name ="busService";
	static public final String AdminService_Name ="adminService";
	static public final String CarleaseService_Name ="carleaseService";
	static public final String ZeroStr ="0";
	static public final String OneStr ="1";

	public static final String JINQIAO_FENCE = "金桥开发区电子围栏（不能改）";
}
