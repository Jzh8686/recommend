package com.gyj.gx.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imagem/**").addResourceLocations("file:D:/code/barcode/");
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/image/");
    }

}
