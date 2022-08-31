package com.example.library_mysql.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置静态资源映射
 **/
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * keyPath 图片地址
     */
    @Value("${spring.servlet.multipart.location}")
    public String keyPath;

    /**
     * springboot 无法直接访问静态资源，需要放开资源访问路径。
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fullPath = "file:" + keyPath;
        registry.addResourceHandler("res/**").addResourceLocations(fullPath);

        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
    }
}
