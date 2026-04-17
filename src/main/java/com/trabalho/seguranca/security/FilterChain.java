package com.trabalho.seguranca.security;


import com.trabalho.seguranca.model.entity.Papel;
import com.trabalho.seguranca.model.entity.Usuario;
import com.trabalho.seguranca.model.repositories.PapelRepository;
import com.trabalho.seguranca.model.repositories.UsuarioRepository;
import com.trabalho.seguranca.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class FilterChain {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilter filter) throws Exception{
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization ->{
                    authorization.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorization.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    authorization.requestMatchers(HttpMethod.POST, "/usuarios/novo").permitAll();
                    authorization.requestMatchers(HttpMethod.GET, "/usuarios") .hasAnyRole("USER", "ADMIN");
                    authorization.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN");
                    authorization.requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADMIN");
                    authorization.anyRequest().authenticated();
                        }
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CommandLineRunner init(
            PapelRepository papelRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            Papel userRole = papelRepository.findByPapel("USER")
                    .orElseGet(() -> papelRepository.save(new Papel(null, "USER")));

            Papel adminRole = papelRepository.findByPapel("ADMIN")
                    .orElseGet(() -> papelRepository.save(new Papel(null, "ADMIN")));

            if (usuarioRepository.findByEmail("user@email.com") == null) {
                Usuario user = new Usuario();
                user.setNome("Usuario");
                user.setSobrenome("Comum");
                user.setEmail("user@email.com");
                user.setSenha(passwordEncoder.encode("1234"));
                user.setPapeis(List.of(userRole));

                usuarioRepository.save(user);
            }

            if (usuarioRepository.findByEmail("admin@email.com") == null) {
                Usuario admin = new Usuario();
                admin.setNome("Admin");
                admin.setSobrenome("Sistema");
                admin.setEmail("admin@email.com");
                admin.setSenha(passwordEncoder.encode("1234"));
                admin.setPapeis(List.of(adminRole));

                usuarioRepository.save(admin);
            }
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "http://127.0.0.1:5173"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

}
