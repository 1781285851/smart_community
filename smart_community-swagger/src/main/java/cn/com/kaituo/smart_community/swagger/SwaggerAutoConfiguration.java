package cn.com.kaituo.smart_community.swagger;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import cn.com.kaituo.smart_community.swagger.properties.SoftworksApiInfoProperties;
import cn.com.kaituo.smart_community.swagger.properties.SoftworksContact;
import cn.com.kaituo.smart_community.swagger.properties.SoftworksDocketProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * * Licensed to HOMEII,Inc. under the terms of the HOMEII
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * ----------------------------------------------------------------------------
 *   Date           Author               Version        Comments
 *  2017-10-23    zhengjun.jing           1.0            Initial Version

 */


@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable")
@EnableConfigurationProperties(value = { SwaggerProperties.class })
public class SwaggerAutoConfiguration {
    @Autowired
    SwaggerProperties properties;

    @Autowired
    Environment env;

    @Bean
    public Docket petApi(){
    	SoftworksDocketProperties docket = properties.getDocket();
//        List<ResponseMessage> messages = new ArrayList<>();
//        ResponseMessage message1 = new ResponseMessageBuilder().code(200).message("操作成功").responseModel(
//                new ModelRef(WSResponse.class.getTypeName())).build();
//        ResponseMessage message2 = new ResponseMessageBuilder().code(400).message("非法请求").responseModel(
//                new ModelRef(WSResponse.class.getTypeName())).build();
//        ResponseMessage message3 = new ResponseMessageBuilder().code(501).message("如请求路径拼写不正确").responseModel(
//                new ModelRef(WSResponse.class.getTypeName())).build();
//        ResponseMessage message4 = new ResponseMessageBuilder().code(502).message("服务器过载引起的错误").responseModel(
//                new ModelRef(WSResponse.class.getTypeName())).build();
//
//        messages.add(message1);
//        messages.add(message2);
//        messages.add(message3);
//        messages.add(message4);

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(inforhmApiInfo()).groupName(docket.getGroupName()).select()
                .apis(withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
                .pathMapping("/").directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false);
//                .globalResponseMessage(RequestMethod.GET, messages)
//                .globalResponseMessage(RequestMethod.POST, messages)
//                .globalResponseMessage(RequestMethod.PUT, messages);

    }

    private ApiInfo inforhmApiInfo(){
    	SoftworksApiInfoProperties apiInfo = properties.getApiInfo();
    	SoftworksContact contact = apiInfo.getContact();
        return new ApiInfoBuilder().title(apiInfo.getTitle()).description(apiInfo.getDescription()).termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .contact(new springfox.documentation.service.Contact(contact.getName(),contact.getUrl(),contact.getEmail()))
                .version(apiInfo.getVersion()).license(apiInfo.getLicense()).licenseUrl(apiInfo.getLicenseUrl()).build();

    }
}
