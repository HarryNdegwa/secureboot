package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import com.example.demo.security.jwt.JwtTokenVerifier;
import com.example.demo.security.jwt.JwtUsernameAndPasswordAuthFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private ApplicationUserService applicationUserService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // authorize all requests using http basic authentication
                http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
                                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthFilter.class)
                                .authorizeRequests().antMatchers("/", "/css/*", "/js/*").permitAll()
                                // giving access to students
                                .antMatchers("/api/**").hasRole(UserRoles.STUDENT.name())
                                // limiting access to users with different permissions
                                // .antMatchers(HttpMethod.POST, "/management/api/**")
                                // .hasAuthority(UserPermissions.COURSE_WRITE.getPermission())
                                // .antMatchers(HttpMethod.PUT, "/management/api/**")
                                // .hasAuthority(UserPermissions.COURSE_WRITE.getPermission())
                                // .antMatchers(HttpMethod.DELETE, "/management/api/**")
                                // .hasAuthority(UserPermissions.COURSE_WRITE.getPermission())
                                // giving access to both admins and admin trainees
                                // .antMatchers(HttpMethod.GET, "/management/api/**")
                                // .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ADMINTRAINEE.name())
                                .anyRequest().authenticated();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(daoAuthenticationProvider());
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setPasswordEncoder(passwordEncoder);
                provider.setUserDetailsService(applicationUserService);
                return provider;
        }

        // @Override
        // @Bean
        // protected UserDetailsService userDetailsService() {
        // UserDetails user =
        // User.builder().username("harry").password(passwordEncoder.encode("password"))
        // // .roles(UserRoles.STUDENT.name())
        // .authorities(UserRoles.STUDENT.getGrantedAuthorities()).build();

        // UserDetails admin =
        // User.builder().username("mike").password(passwordEncoder.encode("password1234"))
        // // .roles(UserRoles.ADMIN.name())
        // .authorities(UserRoles.ADMIN.getGrantedAuthorities()).build();

        // UserDetails adminTrainee =
        // User.builder().username("tom").password(passwordEncoder.encode("password"))
        // // .roles(UserRoles.ADMINTRAINEE.name())
        // .authorities(UserRoles.ADMINTRAINEE.getGrantedAuthorities()).build();
        // return new InMemoryUserDetailsManager(user, admin, adminTrainee);

        // }
}
