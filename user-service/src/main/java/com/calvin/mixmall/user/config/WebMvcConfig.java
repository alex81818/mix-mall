package com.calvin.mixmall.user.config;

import com.calvin.mixmall.user.interceptor.LoginInterceptor;
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
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register")
                .excludePathPatterns("/api/goods/pagelist/**")
                .excludePathPatterns("/api/banner/list")
                .excludePathPatterns("/api/goods/**")
                .excludePathPatterns("/doc.html", "/webjars/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
