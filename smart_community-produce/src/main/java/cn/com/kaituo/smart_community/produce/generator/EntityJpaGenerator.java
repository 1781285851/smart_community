package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.produce.utils.StringParser;
import lombok.extern.log4j.Log4j2;

import java.io.*;

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
public class EntityJpaGenerator extends EntityGenerator{
    @Override
    String getName() {return "EntityGenerator";}

    @Override
    String getDescribe() {
        return "默认模型层代码生成器。代码规范:Jpa,Hibernate";
    }

    @Override
    String targetSuffix() {return "Entity.java"; }

    @Override
    void sourceProduce(FrameworkGenerator framework, ResourceEntity entity) {

    }

    @Override
    public void sourceProduce(ResourceEntity entity, String baseFolder, String sourceFolder, String javaBasePackage) {
        log.debug("生成JPA模型层代码" +entity.getName());
    }

    void produceJavaByFreemarker(String baseFolder, String basePackage, ResourceEntity entity){
        String entityTemplate ="entity.ftl";

        produceJavaEntityByFreemarker(entityTemplate,baseFolder,basePackage,entity);
    }

    private void produceJavaEntityByFreemarker(String entityTemplate, String baseFolder, String basePackage, ResourceEntity entity){
        log.debug("Entity代码生成: baseFolder=" +baseFolder +",basePackage=" +basePackage +",entity=" +entity.getName());
        File targetDir=new File(baseFolder + packageToPath(basePackage +".main.model"));
        if(!targetDir.exists())
            targetDir.mkdirs();

        entity.setJavaPackage(basePackage);
        String targetFileStr = targetDir.getPath() +File.separator + StringParser.upperFirstLatter(entity.getName()) +targetSuffix();
        log.debug("即将生成目标文件：" +targetFileStr);
        try {
            OutputStreamWriter write =new OutputStreamWriter(new FileOutputStream(targetFileStr),"UTF-8");
            getTemplate(entityTemplate).process(entity,write);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}