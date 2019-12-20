package cn.com.kaituo.smart_community.project.service;

import cn.com.kaituo.smart_community.project.model.Module;
import cn.com.kaituo.smart_community.project.model.repo.ModuleRepository;
import cn.com.kaituo.smart_community.project.model.repo.ProjectRepository;
import cn.com.kaituo.smart_community.project.model.Project;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public Boolean delete(Long projectId){
        try {
            projectRepository.deleteById(projectId);
            return true;
        }
        catch (Exception e){
            log.warn("删除项目失败！" +e.getMessage());
            return false;
        }
    }

    public Page<Project> list(Pageable pageable){
        return projectRepository.findProjectByProjectIdIsNull(pageable);
    }

    public Project detail(Long projectId){
        return projectRepository.getOne(projectId);
    }

    public Module moduleDetail(Long moduleId){
        return moduleRepository.getOne(moduleId);
    }
}
