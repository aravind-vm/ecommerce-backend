package com.litmus7.training.ecommerce.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.litmus7.training.ecommerce.backend.config.filters.CustomAthorizationFilter;
import com.litmus7.training.ecommerce.backend.config.filters.CustomAuthenticationFilter;
import com.litmus7.training.ecommerce.backend.dao.UserRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private UserRepository userRepo;

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(getPasswordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("POST"));
		CustomAuthenticationFilter customAuthFilter = new CustomAuthenticationFilter(authenticationManagerBean(),
				userRepo);
		customAuthFilter.setFilterProcessesUrl("/login");
		http.authorizeHttpRequests().antMatchers("/login").permitAll().and().cors().disable();
		// .configurationSource(request -> corsConfiguration);
		;
//		http.authorizeHttpRequests().antMatchers("/api/users/signup").permitAll();
//		http.authorizeHttpRequests().antMatchers("/api/category/**").permitAll();
//		http.authorizeHttpRequests().antMatchers("api/images/**/*").permitAll();

		// antMatchers("/api/users/signup", "/api/category/**",
		// "api/products/category/**",
		// "api/sku/product/**", "api/images/**").permitAll();
		http.httpBasic().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/order/**", "/api/users")
				.authenticated().and().addFilter(customAuthFilter)
				.addFilterBefore(new CustomAthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.formLogin();

	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}
