package ca.flearning.restfulgloom.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${ca.flearning.restfulgloom.api.security.enabled}")
	private boolean secure = false;
	
	@Autowired
	private DataSource datasource;

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//We can effectively disable spring security
		if(!secure) {
			http.authorizeRequests().antMatchers("/**").permitAll();
			System.out.println("  >> Warning: Spring security disabled");
		}else {
		
		// TODO: even for the permitAll() paths, the auth filter is run and puts
		// 		junk in the command-line logs.
		http.authorizeRequests()
			// root, home, & registration open to public
			.antMatchers("/api", "/api/auth**").permitAll()
			//allow h2 console access to admins only
//			.antMatchers("/h2-console/**").hasRole("Admin")
			// force JWT authentication on all endpoints
			.antMatchers("/api/dm**").authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()));
			// require Google oath2
//			.anyRequest().authenticated().and().oauth2Login();
		}

		// disable session creation on Spring Security (JSESSIONID);
		// don't need it since we have a custom JWT
		// TODO: there's still a JSESSIONID ... not sure why
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.csrf()
			// Don't apply CSRF protection to /h2-console. 
			// Not safe, but hey - it's fine for development
			.ignoringAntMatchers("/h2-console/**", "/api/**");
		http.headers()
			//allow use of frame to same origin urls
			.frameOptions()
			.sameOrigin();
	}
	 
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
