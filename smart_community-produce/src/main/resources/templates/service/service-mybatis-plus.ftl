package ${javaPackage}.service;

import ${javaPackage}.model.${name}Entity;
import ${javaPackage}.model.mapper.${name}Mapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;

/**
* Licensed to Homeii LTD. under the terms of the Homeii
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional
* information regarding copyright ownership.
* ----------------------------------------------------------------------------
* Date             Author               Version        Comments
* ${.now?string("yyyy-MM-dd")}        Congcong cc.         1.0            Initial Version
*/

@Service
@Log4j
public class ${name}Service {

	@Autowired
	private ${name}Mapper repository;

	@Transactional
	public Boolean save(${name}Entity entity){
		try{
			repository.insert(entity);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Transactional
	public Boolean update(${name}Entity entity){
		try{
			repository.update(entity,Wrappers.<${name}Entity>query().eq("id",entity.getId()));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public Boolean delete(Long id){
		try{
			repository.deleteById(id);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public ${name}Entity getOne(Long id){
		return repository.selectById(id);
	}

	public List<${name}Entity> getAll(){
		return repository.selectList(Wrappers.<${name}Entity>query().orderByDesc("id"));
	}
}