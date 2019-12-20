package cn.com.kaituo.smart_community.common.viewdata.resource;

import lombok.*;

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
@ToString(callSuper =true)
@EqualsAndHashCode(callSuper=true)
public class ResourceRepository extends ResourceActClass {
	private ResourceEntity bindEntity;
}
