package priv.eternasparkle.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"priv.eternasparkle.mapper"})
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor MyBatisPlusIntercepter() {
        // {1} 创建拦截器。
        MybatisPlusInterceptor MPI = new MybatisPlusInterceptor();
        // {2} 添加一个分页拦截器。
        MPI.addInnerInterceptor(new PaginationInnerInterceptor());
        return MPI;
    }
}