package ro.msg.learning.shop.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    @Profile("with-basic")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/orders/**", "/products/**", "/product-categories/**", "/locations/**", "/stocks/**").authenticated()
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
}
