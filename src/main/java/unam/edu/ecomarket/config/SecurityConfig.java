package unam.edu.ecomarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import unam.edu.ecomarket.servicios.UsuarioDetallesServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final UsuarioDetallesServicio usuarioDetallesServicio;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    public SecurityConfig(UsuarioDetallesServicio usuarioDetallesServicio) {
        this.usuarioDetallesServicio = usuarioDetallesServicio;

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(usuarioDetallesServicio);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si es necesario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/public/**").permitAll() // Rutas públicas
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMINISTRADOR") // Rutas públicas
                        .anyRequest().authenticated() // Protege todas las demás rutas

                )

                .formLogin(form -> form
                        .loginPage("/login") // Página personalizada para el login
                        .defaultSuccessUrl("/home", true) // Redirección después del login exitoso
                        .permitAll()
                )
                .userDetailsService(usuarioDetallesServicio)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

}



