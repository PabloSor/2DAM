package org.iesch.ad.DemoFiltros_ModelosUsuario.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("public/**").permitAll()
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Tod0 lo que no hay dentro de mi lambda lo deja por defecto

        return http.build();
    }
}
