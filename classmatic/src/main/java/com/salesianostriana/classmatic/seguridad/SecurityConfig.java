package com.salesianostriana.classmatic.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImp userDetailsServiceImp;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**").permitAll()
                .antMatchers("/jf/**").hasRole("JF")
                .antMatchers("/profesor/**").hasRole("PROFESOR")
                .antMatchers("/ALUMNO/**").hasRole("ALUMNO")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }


}
