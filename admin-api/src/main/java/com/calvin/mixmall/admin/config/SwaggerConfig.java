package com.calvin.mixmall.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 */

@Configuration //必须存在
@EnableSwagger2 //必须存在
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }


    /**
     * name:开发者姓名
     * url:开发者网址
     * email:开发者邮箱
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理后台接口")//标题
                .description("管理后台接口描述")//文档接口的描述
                .version("1.0.0")//版本号
                .build();
    }
}

