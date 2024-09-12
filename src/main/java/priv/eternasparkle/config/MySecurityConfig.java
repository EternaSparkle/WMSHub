package priv.eternasparkle.config;


import priv.eternasparkle.controller.CustomAccessDeniedHandler;
import priv.eternasparkle.controller.CustomAuthenticationEntryPoint;
import priv.eternasparkle.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/Common/login","Common/register","/Common/loginWithBody","/Axios/data1").anonymous()
                .antMatchers("/Common/getPerms/**","/Common/getMenus/**","/Options/**").permitAll()
                .antMatchers("/Dept/view").hasAnyAuthority("system:dept:")
                .antMatchers("/System/User/listUser/**").hasAuthority("system:user:list_user:")
                .antMatchers("/System/User/addUser").hasAuthority("system:user:add_user:")
                .antMatchers("/System/User/updateUser").hasAuthority("system:user:update_user:")
                .antMatchers("/System/User/delUser/**").hasAuthority("system:user:del_user:")
                .antMatchers("/System/User/getUser/**").hasAuthority("system:user:get_user:")
                .antMatchers("/Dept/**").hasRole("manager")
                .anyRequest().authenticated();
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }


}
