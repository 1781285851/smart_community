package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
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
public class RepositoryJpaGenerator extends RepositoryGenerator{
    @Override
    String getName() {return "RepositoryGenerator";}

    @Override
    String getDescribe() {
        return "默认持久层代码生成器。代码规范:Jpa,Hibernate";
    }

    @Override
    String targetSuffix() {return "Model.java"; }

    @Override
    void sourceProduce(FrameworkGenerator framework, ResourceEntity entity) {

    }

    @Override
    public void sourceProduce(ResourceEntity entity, String baseFolder, String sourceFolder, String javaBasePackage) {
        log.debug("生成Hibernate持久层代码" +entity.getName());
    }

    void produceJavaByFreemarker(String baseFolder, String basePackage, ResourceEntity entity){
        String repositoryTemplate ="repository.ftl";

        produceJavaRepositoryByFreemarker(repositoryTemplate,baseFolder,basePackage,entity);
    }

    private void produceJavaRepositoryByFreemarker(String repositoryTemplate, String baseFolder, String basePackage, ResourceEntity entity){
        log.debug("Repository代码生成: baseFolder=" +baseFolder +",basePackage=" +basePackage +",entity=" +entity.getName());
        log.debug("模板资源位置：" +repositoryTemplate);
    }


}