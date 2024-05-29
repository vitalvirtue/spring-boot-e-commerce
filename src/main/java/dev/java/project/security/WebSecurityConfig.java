package dev.java.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
        @Autowired
        private UserDetailsService userDetailsService;
    
        @SuppressWarnings("deprecation")
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                    .requestMatchers("/public/**").permitAll()
                                    .requestMatchers("/api/**").authenticated()
                                    .anyRequest().authenticated()
                    )
                    .formLogin(withDefaults())
                    // .formLogin(form -> form
                    //                 .loginPage("/public/") // Giriþ sayfasý URL'si
                    //                 .loginProcessingUrl("/login") // Giriþ iþlemi URL'si
                    //                 .successHandler(authenticationSuccessHandler()) // Baþarýlý giriþ iþlemi handler'ý
                    //                 .permitAll() // Giriþ sayfasýna herkes eriþebilir
                    // )
                    .logout(logout -> logout
                                    .logoutSuccessUrl("/public/?logout=true") // Çýkýþ baþarýlý olduðunda yönlendirilecek URL
                                    .deleteCookies("JSESSIONID") // Çýkýþta çerezlerin silinmesi
                                    .permitAll() // Çýkýþ URL'sine herkes eriþebilir
                    )
                    .httpBasic(withDefaults())
                    .csrf().disable();
            return http.build();
        }
    
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userDetailsService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }
    
        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
            return new CustomAuthenticationSuccessHandler();
        }
}
