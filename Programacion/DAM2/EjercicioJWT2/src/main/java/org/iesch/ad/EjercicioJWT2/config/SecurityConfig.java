package org.iesch.ad.EjercicioJWT2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
        )
                .httpBasic(Customizer.withDefaults());
                //.csrf(AbstractHttpConfigurer::disable)
                //.sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
