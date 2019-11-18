package com.cg.onlinebank.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{
    
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()   
        .authorizeRequests()
        
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/customer/**").hasRole("USER")
        .antMatchers("/**").permitAll()
        
                .anyRequest().authenticated()
                .and()
            //.formLogin().and()
            .httpBasic();
        http.headers().frameOptions().disable();
        http.cors();
    }
    
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
}