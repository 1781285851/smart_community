package cn.com.kaituo.smart_community.produce.utils;

import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntity;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceEntityField;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceTable;
import cn.com.kaituo.smart_community.common.viewdata.resource.ResourceTableColumn;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019年10月02日        Congcong cc.         1.0            Initial Version

 */

@Log4j2
public class EntityConverter {
    public static ResourceEntity convertEntity(ResourceTable table){
//        log.debug("转换数据库表为实体模型: " +table.getName());
        ResourceEntity entity =new ResourceEntity();
        entity.setName(StringParser.converterClassName(table.getCode()) +"Entity");
        entity.setTitle(table.getName());
        entity.setComment(table.getComment());
        entity.setTableRefer(StringParser.camelToUnderline(table.getCode()));
        ArrayList<ResourceEntityField> fields =new ArrayList<>();
        entity.setFields(fields);

        table.getColumns().forEach(c->produceEntityField(c,fields));

        return entity;
    }

    private static void produceEntityField(ResourceTableColumn c, ArrayList<ResourceEntityField> fields) {
//        log.debug("转换数据库表字段为实体属性: " +c.getName());
        ResourceEntityField field =new ResourceEntityField();
        field.setName(StringParser.converterFieldName(c.getCode()));
        field.setTitle(c.getName());
        field.setComment(c.getComment());
        field.setColumnRefer(StringParser.camelToUnderline(c.getCode()));
        field.setDataType(convertDataType(c.getDataType()));
        fields.add(field);
    }

    private static String convertDataType(String dataType) {
        if(dataType.toLowerCase().contains("numeric")
                ||dataType.toLowerCase().contains("tinyint")
                ||dataType.toLowerCase().contains("smallint")
                ||dataType.toLowerCase().contains("mediumint")){
            return "Integer";
        }else if(dataType.toLowerCase().contains("date")
                ||dataType.toLowerCase().contains("datetime")
                ||dataType.toLowerCase().contains("timestamp")
                ||dataType.toLowerCase().contains("time")
                ||dataType.toLowerCase().contains("year")){
            return "Timestamp";
        }else if(dataType.toLowerCase().contains("blob")){
            return "Byte[]";
        }else if(dataType.toLowerCase().equals("int")
                ||dataType.toLowerCase().equals("id")
                ||dataType.toLowerCase().equals("integer")
                ||dataType.toLowerCase().equals("long")){
            return "Long";
        }else if(dataType.toLowerCase().contains("float")){
            return "Float";
        }else if(dataType.toLowerCase().contains("double")){
            return "Double";
        }else if(dataType.toLowerCase().equals("bit")){
            return "Boolean";
        }else if(dataType.toLowerCase().contains("decimal")){
            return "BigDecimal";
        }else if(dataType.toLowerCase().contains("bigint")){
            return "BigInteger";
        }else
        return "String";
    }
}
