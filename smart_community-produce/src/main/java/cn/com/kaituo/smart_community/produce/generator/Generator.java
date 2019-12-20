package cn.com.kaituo.smart_community.produce.generator;

import lombok.extern.log4j.Log4j2;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019年7月17日        Congcong cc.         1.0            Initial Version

 */
@Log4j2
public abstract class Generator {
    enum Category {Framework,Layer}
    abstract String getName();
    abstract String getDescribe();
    abstract Category getCategory();

}
