package com.carcaratec.embraer.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    CustomUserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/create", "/users/create/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/users/login")
                .permitAll().successHandler(customAuthenticationSuccessHandler());
        http
                .logout()
                .logoutUrl("/logout") // Define a URL para o logout
                .logoutSuccessUrl("/login?logout") // Define a URL de redirecionamento após o logout
                .invalidateHttpSession(true) // Invalida a sessão do usuário
                .deleteCookies("JSESSIONID"); // Remove os cookies, se necessário
//        http.authorizeRequests()
//                .antMatchers("/swagger-ui.html", "/swagger-ui/", "/v3/api-docs/")
//                .permitAll()
//                .anyRequest()
//                .authenticated();

    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }


}