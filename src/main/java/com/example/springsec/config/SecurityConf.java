package com.example.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Denis").password(encoder().encode("qwerty1"))
                .authorities("read", "write")
                .and()
                .withUser("Elena").password(encoder().encode("qwerty2"))
                .authorities("read")
                .and()
                .withUser("Sergey").password(encoder().encode("qwerty3"))
                .authorities("write")
                .and()
                .withUser("Vasily").password(encoder().encode("qwerty4"))
                .roles("READ", "WRITE")
                .and()
                .withUser("Maxim").password(encoder().encode("qwerty5"))
                .roles("READ")
                .and()
                .withUser("Petr").password(encoder().encode("qwerty5"))
                .roles("Write");
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
