package com.example.springsec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Denis").password("{noop}qwerty1").authorities("read","write")
                .and()
                .withUser("Elena").password("{noop}qwerty2").authorities("read")
                .and()
                .withUser("Sergey").password("{noop}qwerty3").authorities("write");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/start").permitAll()
                .and()
                .authorizeRequests().antMatchers("/read").hasAuthority("read")
                .and()
                .authorizeRequests().antMatchers("/write").hasAuthority("write")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
