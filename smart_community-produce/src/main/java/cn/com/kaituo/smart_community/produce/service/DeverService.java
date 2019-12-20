package cn.com.kaituo.smart_community.produce.service;

import cn.com.kaituo.smart_community.produce.model.ProduceDeveloper;
import cn.com.kaituo.smart_community.produce.model.ProduceDever;
import cn.com.kaituo.smart_community.produce.model.repo.DeveloperRepository;
import cn.com.kaituo.smart_community.produce.model.repo.DeverRepository;
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
public class DeverService {
    @Autowired
    DeverRepository deverRepository;
    @Autowired
    DeveloperRepository developerRepository;

    public ProduceDever save(ProduceDever produceDever){
        return deverRepository.save(produceDever);
    }

    public Boolean delete(Long id){
        try {
            deverRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            log.warn("删除开发机器人模板失败！" +e.getMessage());
            return false;
        }
    }

    public List<ProduceDever> listAll(){
        return deverRepository.findAll();
    }

    public List<ProduceDever> listValid(){
        return deverRepository.findValid();
    }

    public ProduceDever find(Long id){
        return deverRepository.getOne(id);
    }

    /**
     * 获取派生成员
     * @param deverId
     * @return
     */
    public ProduceDeveloper derive(Long deverId){
        ProduceDeveloper developer =deverRepository.getOne(deverId).derive();
        Long jobNumber =developerRepository.getNextJobNumber();
        if(null ==jobNumber)
            jobNumber =1L;
        else
            ++jobNumber;
        log.debug("获取成员工号：" +jobNumber);
        developer.setJobNumber(jobNumber);

        developer =developerRepository.save(developer);
        return developer;
    }
}
