package com.platzi.pizzeria.config.security.config;

import com.platzi.pizzeria.config.security.jwt.JwtRequestFilter;
import com.platzi.pizzeria.service.PermissionRoleEvaluator;
import com.platzi.pizzeria.service.UserSecurityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final UserSecurityService userSecurityService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserSecurityService userSecurityService, JwtRequestFilter jwtRequestFilter) {
        this.userSecurityService = userSecurityService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    private final String[] ROUTES_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

    private final String[] ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/api/pizza/**",
            "/api/order/**",
            "/api/person/**"
    };

    private final String[] ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/api/auth/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(ROUTES_ALLOWED_WITHOUT_AUTHENTICATION).permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET,ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION).permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST,ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION).permitAll();
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                });
                //.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll());
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService memoryUsers(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(passwordEncoder().encode("4321"))
//                .roles("CUSTOMER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, customer);
//    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userSecurityService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true);
        return web -> web.httpFirewall(firewall);
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(HttpSession httpSession){
        var expresionHandler = new DefaultMethodSecurityExpressionHandler();
        expresionHandler.setPermissionEvaluator(new PermissionRoleEvaluator(httpSession));
        return expresionHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
