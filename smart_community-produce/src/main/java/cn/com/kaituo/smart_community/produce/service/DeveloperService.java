package cn.com.kaituo.smart_community.produce.service;

import cn.com.kaituo.smart_community.produce.model.ProduceDeveloper;
import cn.com.kaituo.smart_community.produce.model.repo.DeveloperRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Log4j2
@Service
public class DeveloperService {
    @Autowired
    DeveloperRepository developerRepository;

    public Boolean stop(Long developerId){
        developerRepository.stop(developerId);
        return true;
    }

    public Boolean delete(Long id){
        try {
            developerRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            log.warn("删除开发机器人失败！" +e.getMessage());
            return false;
        }
    }

    public List<ProduceDeveloper> listValid(){
        return developerRepository.findAllValid();
    }

    public List<ProduceDeveloper> listAll(){
        return developerRepository.findAll();
    }

    public ProduceDeveloper find(Long id){
        return developerRepository.getOne(id);
    }
}
