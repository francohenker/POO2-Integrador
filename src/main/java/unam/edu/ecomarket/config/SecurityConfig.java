package unam.edu.ecomarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import unam.edu.ecomarket.repositorios.UsuarioRepositorio;
import unam.edu.ecomarket.servicios.UsuarioDetallesServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    private final UsuarioRepositorio usuarioRepositorio;

    public SecurityConfig(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetallesServicio(usuarioRepositorio);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/**", "/register").permitAll()
//                                .requestMatchers("/*").permitAll()
                                .anyRequest().authenticated()
                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")
//                                .defaultSuccessUrl("/", true)
//                                .failureUrl("/login?error=true")
//                                .permitAll()
//                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .permitAll()
                );
        return http.build();
    }
}



