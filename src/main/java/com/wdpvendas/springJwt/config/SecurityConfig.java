package com.wdpvendas.springJwt.config;

import com.wdpvendas.springJwt.filter.JwtAuthenticationFilter;
import com.wdpvendas.springJwt.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
                        "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
                        "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
                        "/api/test/**", "/authenticate" };

        private final UserDetailsServiceImp userDetailsServiceImp;

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        private final CustomLogoutHandler logoutHandler;

        public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                        JwtAuthenticationFilter jwtAuthenticationFilter,
                        CustomLogoutHandler logoutHandler) {
                this.userDetailsServiceImp = userDetailsServiceImp;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.logoutHandler = logoutHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http
                        .cors(Customizer.withDefaults())
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(
                                                req -> req.requestMatchers("/login/**", "/register/**",
                                                                "/refresh_token/**")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.POST, "/api/produtos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/produtos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/produtos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/produtos/**")
                                                                .permitAll()

                                                                .requestMatchers(HttpMethod.POST, "/api/usuarios/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/usuarios/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/usuarios/**")
                                                                .permitAll()

                                                                .requestMatchers(HttpMethod.POST, "/api/categorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/categorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE,
                                                                                "/api/categorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/categorias/**")
                                                                .permitAll()

                                                                .requestMatchers(HttpMethod.POST,
                                                                                "/api/subcategorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT,
                                                                                "/api/subcategorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE,
                                                                                "/api/subcategorias/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET,
                                                                                "/api/subcategorias/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/api/marca/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/marca/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/marca/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/marca/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/api/cores/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/cores/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/cores/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/cores/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/api/tamanhos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/tamanhos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/tamanhos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/tamanhos/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/api/enderecos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/enderecos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/enderecos/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/enderecos/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/api/banners/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/api/banners/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/api/banners/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/api/banners/**",
                                                                                "/uploads/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.POST, "/imagens/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/imagens/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, "/imagens/**")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/imagens/**")
                                                                .permitAll()


                                                                .requestMatchers(HttpMethod.GET, WHITE_LIST_URL)
                                                                .permitAll()

                                                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
                                                                .anyRequest()
                                                                .authenticated())
                                .userDetailsService(userDetailsServiceImp)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling(
                                                e -> e.accessDeniedHandler(
                                                                (request, response, accessDeniedException) -> response
                                                                                .setStatus(403))
                                                                .authenticationEntryPoint(new HttpStatusEntryPoint(
                                                                                HttpStatus.UNAUTHORIZED)))
                                .logout(l -> l
                                                .logoutUrl("/logout")
                                                .addLogoutHandler(logoutHandler)
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()))
                                .build();

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

}
