package cn.com.kaituo.smart_community.produce.service;

import cn.com.kaituo.smart_community.produce.generator.*;
import cn.com.kaituo.smart_community.produce.utils.PdmParser;
import cn.com.kaituo.smart_community.produce.utils.StringParser;
import com.alibaba.fastjson.JSONObject;
import cn.com.kaituo.smart_community.common.http.SoftworksResponse;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceTable;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceTableColumn;
import cn.com.kaituo.smart_community.common.viewdata.project.Module;
import cn.com.kaituo.smart_community.produce.feign.FeignProjectService;
import cn.com.kaituo.smart_community.produce.feign.FeignResourceService;
import com.inforsw.softworks.produce.generator.*;
import cn.com.kaituo.smart_community.produce.utils.EntityConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.List;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019年7月17日        Congcong cc.         1.0            Initial Version

 */

@Service
@Log4j2
public class GeneratorService {
    @Autowired
    private FreeMarkerConfigurer configurer;
    @Autowired
    FeignResourceService feignResourceService;
    @Autowired
    FeignProjectService feignProjectService;
    @Autowired
    DeveloperService developerService;

    /**
     *调用框架生成器生成实体和持久层、业务层、接口层代码
     * @param tableId
     * @return
     */
    @Transactional
    public Boolean generate(Long moduleId, Long tableId) {
        //准备模块信息
        SoftworksResponse<Module> moduleResult =feignProjectService.moduleDetail(moduleId);
        Module module =moduleResult.getResult();
        log.debug("代码归属模块信息：" +module);

        //准备数据表信息
        SoftworksResponse<ResourceTable> result = feignResourceService.tableDetail(tableId);
        ResourceTable table =result.getResult();
        log.debug("即将生成代码实体名：" +table.getName());
        try {
            ResourceEntity entity =EntityConverter.convertEntity(table);

            //TODO:模板中依赖的是实体的原生名，而不是设计名。后续改进
            entity.setName(entity.getOriginName());

            getFrameworkGenerator(module).sourceProduce(entity);
            feignResourceService.tableExport(tableId);
            return true;
        }
        catch (Exception e) {
            log.warn("代码生成失败！" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    FrameworkSpringCloudGenerator getFrameworkGenerator(Module module){
        FrameworkSpringCloudGenerator fg =new FrameworkSpringCloudGenerator(module.getWorkspace(),module.getBasePackage());
        fg.bindGenerator(new EntityMybatisGenerator());
        fg.bindGenerator(new RepositoryMybatisGenerator());
        fg.bindGenerator(new ServiceMybatisPlusGenerator());
        fg.bindGenerator(new ControllerSpringCloudGenerator());
        return fg;
    }

    /**
     * 导入实体定义
     *
     * @param pdm 包含实体定义的PDM文件
     * @return
     */
    public boolean load(MultipartFile pdm) {
        log.debug("导入实体定义方法，请求参数：[1]" + pdm);
        try {
            InputStream in =pdm.getInputStream();
            PdmParser parser =new PdmParser(in);

            List<PdmParser> tables =parser.getAllTables();

            tables.forEach(t->{
                log.debug("获取到 table： (" +t.getCode() +")" +t.getName());
                String predefineType =t.getPredefineType();
                if(StringUtils.endsWithIgnoreCase(predefineType,"ENTITY")){
                    log.debug("检测到预定义实体，准备导入");
                    loadEntity(t);
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("导入实体定义失败！异常信息：" + e.getMessage());
            return false;
        }
    }

    void loadEntity(PdmParser table){
        ResourceTable resourceTable =new ResourceTable();
        resourceTable.setName(table.getName());
        resourceTable.setOriginCode(table.getCode());
        resourceTable.setCode(StringParser.converterDatabaseCode(table.getCode()));
        resourceTable.setComment(table.getComment());
        resourceTable.setLastModify(Long.valueOf(table.getModificationDate()));

        List<PdmParser> columns =table.getColumns();
        columns.forEach(c->loadColumn(c,resourceTable));

        try{
            ResourceTable result= feignResourceService.saveTable(resourceTable).getResult();
            log.info("成功保存数据库表！(" +table.getCode() +")" +table.getName());
        }
        catch (Exception e){
            log.warn("保存数据库表失败！" +e.getMessage());
            e.printStackTrace();
        }
    }

    void loadColumn(PdmParser column, ResourceTable table){
        ResourceTableColumn rtc =new ResourceTableColumn();
        rtc.setName(column.getName());
        rtc.setOriginCode(column.getCode());
        rtc.setCode(StringParser.converterDatabaseCode(column.getCode()));
        rtc.setComment(column.getComment());
        rtc.setDataType(column.getDataType());
        rtc.setLastModify(Long.valueOf(column.getModificationDate()));
        if(StringUtils.isNotEmpty(column.getLength()))
            rtc.setColumnLength(Integer.valueOf(column.getLength()));

        log.debug("已导入实体属性：(" +rtc.getCode() +")" +rtc.getName());
        table.getColumns().add(rtc);
    }

    @PostConstruct
    public void register(){
        log.debug("启动时注册所有可用代码生成器");
//        GeneratorStore.generatorRegister(new FrameworkSpringCloudGenerator());
        GeneratorStore.generatorRegister(new EntityJpaGenerator());
        GeneratorStore.generatorRegister(new EntityMybatisGenerator());
        GeneratorStore.generatorRegister(new RepositoryJpaGenerator());
        GeneratorStore.generatorRegister(new RepositoryMybatisGenerator());
        GeneratorStore.generatorRegister(new ServiceMybatisPlusGenerator());
        GeneratorStore.generatorRegister(new ControllerSpringCloudGenerator());

        //FreemarkerConfigurer
        FreemarkerGenerator.setConfigurer(configurer);
        log.debug("FreemarkerConfigurer: " +configurer.getConfiguration());

//        log.debug("开发机器人线程初始化");
//        list<>.parallelStream().forEach 才启动多线程
    }

    /**
     * 获取所有注册的代码生成器
     * @return JSONObject
     */
    public JSONObject generators(){
        return GeneratorStore.getGenerators();
    }
}
