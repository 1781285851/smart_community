package cn.com.kaituo.smart_community.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019-09-27        Congcong cc.         1.0            Initial Version
 */
@Converter
public class SkillsConverter implements AttributeConverter<List<Skills>, String> {

    @Override
    public String convertToDatabaseColumn(List<Skills> attribute) {
        StringBuffer skillsStr =new StringBuffer();
        attribute.forEach(s ->{
            if(skillsStr.length() >0)
                skillsStr.append(",");
            skillsStr.append(s.name());
        });
        return skillsStr.toString();
    }

    @Override
    public List<Skills> convertToEntityAttribute(String dbData) {
        List<Skills> skills =new ArrayList<>();
        String[] skillsStr =dbData.split(",");
        for(int i=0;i< skillsStr.length;i++){
            skills.add(Skills.valueOf(skillsStr[i]));
        }
        return skills;
    }
}