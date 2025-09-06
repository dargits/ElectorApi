package elector.ElcApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint trong ứng dụng
                .allowedOriginPatterns("*") // Cho phép mọi nguồn gốc (domain)
                .allowedMethods("*") // Cho phép mọi phương thức HTTP (GET, POST, PUT, DELETE, OPTIONS)
                .allowedHeaders("*") // Cho phép mọi header trong request
                .allowCredentials(true); // Cho phép gửi cookies và thông tin xác thực
    }
}