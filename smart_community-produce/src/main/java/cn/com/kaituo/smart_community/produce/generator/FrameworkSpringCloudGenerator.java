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
public class FrameworkSpringCloudGenerator extends FrameworkGenerator{
    private String moduleFolder,baseJavaPackage;
    private static final String workspace ="F:\\idea-projects\\";

    public FrameworkSpringCloudGenerator(String moduleFolder, String baseJavaPackage){
        this.moduleFolder = moduleFolder;
        this.baseJavaPackage =baseJavaPackage;
    }
    @Override
    String getName() {return "SpringCloudFramework";}

    @Override
    String getDescribe() {
        return "默认代码框架，代码规范SpringCloud,consul,swagger2,actuator";
    }

    //一站式受理外网接口模块目录 "F:\\idea-projects\\onlineAccept\\onlineAccept-outer"
    //一站式受理外网接口模块包路径 "cn.gov.mohrss.onlineaccept"
    @Override
    String getModuleFolder() { return workspace + moduleFolder; }
    @Override
    String getJavaBasePackage() { return baseJavaPackage; }

    @Override
    String getSourceFolder(){return "src/main/java";}
    String getResourceFolder(){return "src/main/resources";}
    String getTestSourceFolder(){return "src/test/java";}
    String getTestResourceFolder(){return "src/test/resources";}

    /**
     * 支持所有层
     * @param layer
     * @return
     */
    @Override
    Boolean supportLayer(SystemLayer layer) {
        return true;
    }

    public void prepareBaseline() {
        log.info("准备SpringCloud工程基本框架,依赖版本Greenwich.RELEASE");
    }
}
