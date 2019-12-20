package cn.com.kaituo.smart_community.produce.generator;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
public abstract class FreemarkerGenerator extends Generator {
    //Freemarker配置实例
    static FreeMarkerConfigurer fcfg;
    public static void setConfigurer(FreeMarkerConfigurer configurer){fcfg =configurer;}

    Template getTemplate(String template){
        // 指定模板文件从何处加载的数据源，这里设置成一个文件目录
        log.debug("加载模板：" +template);
        try {
            return fcfg.getConfiguration().getTemplate(template);
        } catch (IOException e) {
            log.warn("加载模板失败！" +e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据模板和实体生成Java源文件，如果文件已经存在则放弃；用于repository,service,controller
     * @param template
     * @param entity
     * @param javaFile
     * @throws IOException
     */
    void generateJavaByEntity(String template, ResourceEntity entity,String javaFile) {
        try {
            generateJavaByEntity(template,entity,javaFile,false);
        } catch (IOException e) {
            log.warn("系统IO异常！");
            e.printStackTrace();
        } catch (TemplateException e) {
            log.warn("模板处理异常！");
            e.printStackTrace();
        }
    }

    /**
     * 根据模板和实体生成Java源文件，如果文件已经存在则覆盖；用于实体类更新
     * @param template
     * @param entity
     * @param javaFile
     * @throws IOException
     */
    void generateJavaOverwrite(String template, ResourceEntity entity,String javaFile) {
        try {
            generateJavaByEntity(template,entity,javaFile,true);
        } catch (IOException e) {
            log.warn("系统IO异常！");
            e.printStackTrace();
        } catch (TemplateException e) {
            log.warn("模板处理异常！");
            e.printStackTrace();
        }
    }

    private void generateJavaByEntity(String template, ResourceEntity entity,String javaFile,Boolean overwrite) throws IOException, TemplateException {
            File targetFile =new File(javaFile);
            if(targetFile.exists() &&!overwrite){
                log.info("目标文件已存在，不重复生成！" +javaFile);
                return;
            }

            log.debug("即将生成目标文件：" +javaFile);
            OutputStreamWriter write =new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8");

            targetFile.getParentFile().mkdirs();
            getTemplate(template).process(entity,write);
            write.close();
    }
}
