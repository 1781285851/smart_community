package cn.com.kaituo.smart_community.core.service;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.log4j.Log4j2;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * * Licensed to CMDT,Inc. under the terms of the CMDT
 * Software License version 1.0.
 * <p>
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 * Date           Author               Version        Comments
 * 10/6/2017       congcong         1.0            Initial Version
 * 10/10/2017		congcong		1.1				fix result url to json
 */

@Log4j2
@Component
@EnableAutoConfiguration
public class FileStorageService {


    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${res_access_url}")
    private String RES_ACCESS_URL;

    /**
     * 上传文件
     * 重载方法
     *
     * @param file
     * @return
     */
    public List<String> uploadFile(List<MultipartFile> file) {
        return file.stream().map(this::uploadFile).collect(Collectors.toList());

    }

    private String uploadFile(MultipartFile file) {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            String accessUrl = getResAccessUrl(storePath);
            log.info(String.format("upload file %s, access-url = %s", file.getOriginalFilename(), accessUrl));
            return accessUrl;
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 上传文件
     * 重载方法 返回List<JSONObject>
     *
     * @param file
     * @return
     */
    public List<JSONObject> uploadFileJSON(List<MultipartFile> file) {
        return file.stream().map(this::uploadFileJSON).collect(Collectors.toList());

    }

    private JSONObject uploadFileJSON(MultipartFile file) {
		try {
    		JSONObject jsonObj =new JSONObject();
			String accessUrl =uploadFile(file);
			jsonObj.put("access-url", accessUrl);

			log.info("json result: " +jsonObj);

    		return jsonObj;
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}
        return null;
    }

    /**
     * 封装文件访问路径
     *
     * @param storePath
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getResAccessUrl(StorePath storePath) {
        return RES_ACCESS_URL + storePath.getFullPath();
    }


    /**
     * 删除文件
     * 重载方法
     *
     * @param fileUrl
     */
    public boolean removeFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());

            log.info(String.format("remove file %s", fileUrl));
            return true;
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }

}
