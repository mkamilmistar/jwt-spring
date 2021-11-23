/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 23/11/2021
 */

/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 23/11/2021
 */

package com.jwt.userservice.configuration.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final CustomAuthenticationEntry customAuthenticationEntry;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;
  private final JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
       .authorizeRequests()
       .antMatchers("/user/v1/list").permitAll()
       .anyRequest().authenticated().and()
       .exceptionHandling().authenticationEntryPoint(customAuthenticationEntry)
       .accessDeniedHandler(customAccessDeniedHandler)
       .and().sessionManagement()
       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs",
      "/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**");
  }
}
