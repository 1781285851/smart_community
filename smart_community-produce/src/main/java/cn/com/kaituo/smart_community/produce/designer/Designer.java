package cn.com.kaituo.smart_community.produce.designer;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-30        Congcong cc.         1.0            Initial Version
 */

public abstract class Designer<T,R> {
    public abstract SystemLayer workLayer();
    public abstract String getName();
    public abstract String getDescribe();
    public abstract R simpleDesigner(T t);
}
