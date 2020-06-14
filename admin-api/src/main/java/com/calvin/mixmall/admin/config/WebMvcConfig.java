package com.calvin.mixmall.admin.config;

import com.calvin.mixmall.admin.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

@Configuration
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/sysuser/login")
                .excludePathPatterns("/doc.html", "/webjars/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/mix_mall_upload/**").addResourceLocations("file:D:/mix_mall_upload/");
    }
}
