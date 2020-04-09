package com.neu.config;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket webApiConfig(){

        //添加head参数start
         ParameterBuilder tokenPar = new ParameterBuilder();
         List<Parameter> pars = new ArrayList<Parameter>();
         tokenPar.name("token").description("token值")
                 .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
         pars.add(tokenPar.build());
         return new Docket(DocumentationType.SWAGGER_2)
                 .select()
                 .apis(RequestHandlerSelectors
                 .basePackage("com"))
                .paths(PathSelectors.any())
                .build()
                 .globalOperationParameters(pars);


//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build();
    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("leetcode")
                .description("本文档描述了leetcode接口定义")
                .version("1.0")
                .contact(new Contact("胡若琛", "http://baidu.com", "82155551@qq.com"))
                .build();
    }



}
