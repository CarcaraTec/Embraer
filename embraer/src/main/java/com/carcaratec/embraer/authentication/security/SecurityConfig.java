package com.carcaratec.embraer.authentication.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@EnableMethodSecurity
    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        CustomUserDetailsService userDetailsService;

        private BCryptPasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            System.out.println(auth);
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeHttpRequests().antMatchers("/usuarios/criar").permitAll()
                    .and().httpBasic();
            http.authorizeHttpRequests().anyRequest().authenticated();
        }

    }



