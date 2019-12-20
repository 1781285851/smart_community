package cn.com.kaituo.smart_community.core.controller;

import com.alibaba.fastjson.JSONObject;
import cn.com.kaituo.smart_community.core.service.FileStorageService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
@RestController
@RequestMapping
@Api("FastDFS文件服务器相关接口")
public class FastdfsController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传文件, 异步接口",response = String.class)
    public Callable<List<String>> uploadFile(@ApiParam("待上传文件") List<MultipartFile> file,
                                             HttpServletRequest request) {
    	log.info("upload file... ");
    	if(file ==null || file.isEmpty()){
    		log.error("无法读取上传文件！");
    		return null;
    	}
        return () -> fileStorageService.uploadFile(file);
    }

    @PostMapping(value = "/upload-single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传文件, 异步接口",response = String.class)
    public Callable<List<String>> uploadSingleFile(@ApiParam("待上传文件") MultipartFile file,
                                             HttpServletRequest request) {
        log.info("upload file... ");
        ArrayList<MultipartFile> files =new ArrayList<>();

        if(file ==null){
            log.error("无法读取上传文件！");
            return null;
        }
        files.add(file);
        return () -> fileStorageService.uploadFile(files);
    }
    
    @PostMapping(value = "/upload-json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传文件, 异步接口",response = String.class)
    public Callable<List<JSONObject>> uploadFileJSON(@ApiParam("待上传文件") List<MultipartFile> file,
                                                     HttpServletRequest request) {
    	log.info("upload file result json... ");
    	if(file ==null || file.isEmpty()){
    		log.error("无法读取上传文件！");
    		return null;
    	}
    	return () -> fileStorageService.uploadFileJSON(file);
    }
    
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除文件, 异步接口")
    public Callable<Boolean> removeFile(@ApiParam("待删除文件的URL") @RequestParam("fileUrl") String fileUrl) {
        return () -> fileStorageService.removeFile(fileUrl);
    }
}
