package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.produce.designer.SystemLayer;
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
public class FrameworkVueGenerator extends FrameworkGenerator{
    @Override
    String getName() {return "SpringCloudFramework";}

    @Override
    String getDescribe() {
        return "默认代码框架，代码规范SpringCloud,consul,swagger2,actuator";
    }

    @Override
    String getModuleFolder() { return null; }

    @Override
    String getJavaBasePackage() {
        log.warn("暂不支持Java生成！");
        return "";
    }

    @Override
    String getSourceFolder(){return "src/main/java";}

    String getResourceFolder(){return "src/main/resources";}
    String getTestSourceFolder(){return "src/test/java";}
    String getTestResourceFolder(){return "src/test/resources";}

    @Override
    Boolean supportLayer(SystemLayer layer) {
        switch (layer){
            case View:
                return true;
            default:
                return false;
        }
    }

    public void prepareBaseline() {
        log.info("准备SpringCloud工程基本框架,依赖版本Greenwich.RELEASE");
    }
}
