package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.produce.designer.SystemLayer;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;

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
public abstract class FrameworkGenerator extends FreemarkerGenerator{
    HashMap<SystemLayer,LayerGenerator> generators =new HashMap<>();
    public void bindGenerator(LayerGenerator generator){
        if(null ==generator) return;
        if(supportLayer(generator.workLayer())){
            generators.put(generator.workLayer(),generator);
            log.debug("框架加载生成器（" +generator.workLayer() +")" +generator.getName());
        }
        else{
            log.warn("框架不支持的生成器（" +generator.workLayer() +")" +generator.getName());
        }
    }
    @Override
    Category getCategory() {return Category.Framework; }

    public void sourceProduce(ResourceEntity entity){
        log.debug("即将生成代码框架");
        prepareBaseline();
        log.debug("即将调用所有已注册的生成器生成源码");
        generators.forEach((layer,generator) ->{
            log.debug("调用 " +layer.name() +" 层生成器：" +generator.getName());
            generator.sourceProduce(this,entity);
        });
    }

    abstract String getModuleFolder();
    abstract String getSourceFolder();
    abstract String getJavaBasePackage();

    abstract Boolean supportLayer(SystemLayer layer);
    abstract void prepareBaseline();
}