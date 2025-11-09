package infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔒 Désactive tout comportement par défaut
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            // 🌐 Autorise les pages publiques
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/",
                    "/home",
                    "/css/**",
                    "/img/**",
                    "/js/**",
                    "/favicon.ico"
                ).permitAll()
                .anyRequest().permitAll() // tu peux changer ça plus tard
            )

            // 🚪 Gestion du logout
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }
}
