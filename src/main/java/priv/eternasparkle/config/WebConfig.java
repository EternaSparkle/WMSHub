package priv.eternasparkle.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("{TRACE}添加跨域映射地址...");
        System.out.println("+---------------------------------+");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63343/","http://localhost:8081/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
