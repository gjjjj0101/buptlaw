package edu.bupt.law.user.config;

import edu.bupt.law.security.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {

        return http.httpBasic(AbstractHttpConfigurer::disable)
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c ->
                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling()
                .and()
                .authorizeRequests(c -> c

                        .anyRequest().permitAll()
                )
                .addFilterBefore(
                        new JWTFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService,
                                                      PasswordEncoder encoder) {
        return authentication -> {
            String username = (String)authentication.getPrincipal();
            String password = (String)authentication.getCredentials();
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
            if (!user.isEnabled()) {
                throw new DisabledException("账户未启用");
            }
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        };
    }


}
