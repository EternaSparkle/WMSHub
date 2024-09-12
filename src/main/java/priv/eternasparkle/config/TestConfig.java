package priv.eternasparkle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author EternaSparkle
 * @Date 2024/08/29
 * @Time 18:53
 */
@Configuration
public class TestConfig {
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService detailsService){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(detailsService);
        return provider;
    }
}
