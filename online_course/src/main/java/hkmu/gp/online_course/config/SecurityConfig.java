package hkmu.gp.online_course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/WEB-INF/**", "/", "/index", "/register", "/login", "/css/**", "/h2-console/**").permitAll()
                        .requestMatchers("/lecture/**", "/poll/**", "/profile/**", "/history/**").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers("/admin/**", "/lecture/add", "/poll/add", "/user/edit/**", "/user/delete/**").hasRole("TEACHER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {{
                            setDefaultTargetUrl("/");
                            setAlwaysUseDefaultTargetUrl(true);
                        }})
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}