package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.produce.designer.SystemLayer;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import lombok.extern.log4j.Log4j2;

import java.io.File;

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
public abstract class LayerGenerator extends FreemarkerGenerator{
    FrameworkGenerator frameworkGenerator;
    @Override
    Category getCategory() {return Category.Layer; }

    void bindFrameworkGenerator(FrameworkGenerator fg){
        frameworkGenerator =fg;
    }
    static String packageToPath(String packageStr){
        StringBuffer pathStr =new StringBuffer();
        String[] packageArr =packageStr.split("\\.");
        for(int i =0;i<packageArr.length;i++)
            pathStr.append(File.separator).append(packageArr[i]);

        log.debug("包转换目录：" +packageStr +" -->" +pathStr.toString());
        return pathStr.toString();
    }

    abstract SystemLayer workLayer();
    abstract String targetSuffix();
    abstract void sourceProduce(FrameworkGenerator framework,ResourceEntity entity);
}
