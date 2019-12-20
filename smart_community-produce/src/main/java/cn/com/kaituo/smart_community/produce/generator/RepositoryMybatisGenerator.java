package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.produce.utils.StringParser;
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
public class RepositoryMybatisGenerator extends RepositoryGenerator{
    @Override
    String getName() {return "RepositoryMybatisGenerator";}

    @Override
    String getDescribe() {
        return "持久层代码规范:Mybatis-plus";
    }

    @Override
    String targetSuffix() { return "Mapper.java"; }

    @Override
    void sourceProduce(FrameworkGenerator framework, ResourceEntity entity) {
        sourceProduce(entity,framework.getModuleFolder(),framework.getSourceFolder(),framework.getJavaBasePackage());
    }

    @Override
    public void sourceProduce(ResourceEntity entity, String baseFolder, String sourceFolder, String javaBasePackage) {
        log.debug("生成Mybatis持久层代码" +entity.getName());
        String template ="mapper.ftl";

        produceJavaRepositoryByFreemarker(template,baseFolder + File.separator +sourceFolder,javaBasePackage,entity);
    }

    private void produceJavaRepositoryByFreemarker(String template, String baseFolder, String basePackage, ResourceEntity entity){
        log.debug("Repository持久层代码生成: baseFolder=" +baseFolder +",basePackage=" +basePackage +",entity=" +entity.getName());
        File targetDir=new File(baseFolder + packageToPath(basePackage +".model.mapper"));

        entity.setJavaPackage(basePackage);
        String targetFileStr = targetDir.getPath() +File.separator + StringParser.upperFirstLatter(entity.getName()) +targetSuffix();

        generateJavaByEntity(template,entity,targetFileStr);
    }
}
