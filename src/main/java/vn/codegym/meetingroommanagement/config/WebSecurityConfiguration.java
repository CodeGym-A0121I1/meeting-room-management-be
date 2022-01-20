package vn.codegym.meetingroommanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.codegym.meetingroommanagement.filter.CustomAccessDeniedHandler;
import vn.codegym.meetingroommanagement.filter.JwtAuthenticationTokenFilter;
import vn.codegym.meetingroommanagement.filter.RestAuthenticationEntryPoint;
import vn.codegym.meetingroommanagement.service.IAccountService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, IAccountService accountService) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.accountService = accountService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final IAccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/equipments/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/rooms/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/**").access("hasRole('ROLE_ADMIN')")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}