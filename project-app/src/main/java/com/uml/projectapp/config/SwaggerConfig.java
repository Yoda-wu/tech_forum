package com.uml.projectapp.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author wuyuda
 * @date 2022-03-13 12:24
 */
@Configuration
public class SwaggerConfig {

    private final BuildProperties properties;

    public SwaggerConfig(BuildProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30).apiInfo(
                apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("接口文档")
                .description("接口调用如果看不懂或者不明确就问我和黄昊城")
                .contact(new Contact("吴昱达","","1286360646@qq.com"))
                .version(properties.getVersion())
                .build();
    }
}
