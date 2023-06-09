package ro.msg.learning.shop.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("cristi")
                .password(passwordEncoder().encode("cristi"))
                .authorities("ROLE_USER");
    }

    @Bean
    @Profile("with-basic")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/orders/**", "/products/**", "/product-categories/**", "/locations/**", "/stocks/**").permitAll()
                .requestMatchers("/", "/**").permitAll()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Profile("with-form")
    public SecurityFilterChain configureWithForm(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/orders/**", "/products/**", "/product-categories/**", "/locations/**", "/stocks/**").permitAll()
                .requestMatchers("/", "/**").permitAll()
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
