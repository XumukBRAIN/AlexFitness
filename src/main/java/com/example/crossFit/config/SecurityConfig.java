package com.example.crossFit.config;

import com.example.crossFit.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    @Override
   protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/auth/login", "/auth/login?error", "/manager/register", "/client/register")
                .permitAll();
              /*.anyRequest().authenticated()
              .and()
              .formLogin().loginPage("/auth/login")
              .loginProcessingUrl("/process_login")//проверяется введенные логин и пароль
              .loginProcessingUrl("/process")//переход на страницу двухфакторной аутентификации
              .failureUrl("/auth/login")//переброс на эту страницу в случае неправильного кода из письма
              .defaultSuccessUrl("/my_account")//пользователь авторизовался
              .failureUrl("/auth/login?error")
              .and()
              .logout()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/auth/login");*/

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
