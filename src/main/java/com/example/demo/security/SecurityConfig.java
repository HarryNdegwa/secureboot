package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // authorize all requests using http basic authentication
                http.authorizeRequests().antMatchers("/", "/css/*", "/js/*").permitAll()
                                // giving access to students
                                .antMatchers("/api/**").hasRole(UserRoles.STUDENT.name())
                                // limiting access to users with different permissions
                                .antMatchers(HttpMethod.POST, "/management/api/**")
                                .hasAuthority(UserPermissions.COURSE_WRITE.name())
                                .antMatchers(HttpMethod.PUT, "/management/api/**")
                                .hasAuthority(UserPermissions.COURSE_WRITE.name())
                                .antMatchers(HttpMethod.DELETE, "/management/api/**")
                                .hasAuthority(UserPermissions.COURSE_WRITE.name())
                                // giving access to both admins and admn trainees
                                .antMatchers(HttpMethod.GET, "/management/api/**")
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ADMINTRAINEE.name()).anyRequest()
                                .authenticated().and().httpBasic();
        }

        @Override
        @Bean
        protected UserDetailsService userDetailsService() {
                UserDetails user = User.builder().username("harry").password(passwordEncoder.encode("password"))
                                .roles(UserRoles.STUDENT.name()).build();

                UserDetails admin = User.builder().username("mike").password(passwordEncoder.encode("password1234"))
                                .roles(UserRoles.ADMIN.name()).build();

                UserDetails adminTrainee = User.builder().username("tom").password(passwordEncoder.encode("password"))
                                .roles(UserRoles.ADMINTRAINEE.name()).build();
                return new InMemoryUserDetailsManager(user, admin, adminTrainee);

        }
}
