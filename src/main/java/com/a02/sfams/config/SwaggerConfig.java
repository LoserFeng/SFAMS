package com.a02.sfams.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


@Bean
public Docket createAdminDocket()
{
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(adminInfo())
            .enable(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.a02.sfams.controller"))
            .paths(PathSelectors.any())
            .build();
}


    private ApiInfo adminInfo()
    {
        return new ApiInfoBuilder()
                .title("高校固定资产管理系统-api文档")
                .description("高校固定资产管理系统接口文档")
                .version("1.0")
                .build();
    }

}
