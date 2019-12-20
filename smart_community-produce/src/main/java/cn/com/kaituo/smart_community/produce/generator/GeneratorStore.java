package cn.com.kaituo.smart_community.produce.generator;

import com.alibaba.fastjson.JSONObject;
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
public class GeneratorStore {
    private static GeneratorStore generator =null;
    private static HashMap<String,LayerGenerator> generatorHashMap =new HashMap<>();
    private static JSONObject generators =new JSONObject();

    public static JSONObject getGenerators(){
        return generators;
    }

    public static LayerGenerator getGenerator(String generatorName){
        return generatorHashMap.get(generatorName);
    }

    public static void generatorRegister(LayerGenerator lg){
        if(generator ==null)
            generator =new GeneratorStore();
        if(!generators.containsKey(lg.getCategory().name()))
            generators.put(lg.getCategory().name(),new JSONObject());
        generators.getJSONObject(lg.getCategory().name()).put(lg.getName(),lg.getDescribe());
        generatorHashMap.put(lg.getName(),lg);
        log.info("注册代码生成器 -->" +lg.getName());
    }
    private GeneratorStore(){}
}
