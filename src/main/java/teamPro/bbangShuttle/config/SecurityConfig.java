package teamPro.bbangShuttle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import teamPro.bbangShuttle.security.JwtAuthenticationFilter;


// ** Spring Boot Security 기본설정 화일
// => spring-boot-starter-security 의 기본설정 disable
// => PasswordEncoder (구현클래스:BCryptPasswordEncoder) Bean 설정

// ** WebSecurityConfigurerAdapter
// => Deprecated 되어 사용불가능 ( java.io.FileNotFoundException 발생 )
//    SecurityFilterChain를 Bean으로 등록해서 사용해야 함.
//	 
/*
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.cors().disable()
         .csrf().disable()
         .formLogin().disable()
         .headers().frameOptions().disable();
   }
} //class
*/

//** @EnableWebSecurity
//=> 스프링 AutoConfiguration이며 우리가 쉽게 MVC Security 세팅을 할 수 있게 도와줌.
//   그러므로 설정파일을 의미하는 @Configuration 는 없어도 됨

//** SpringBoot Auto Configuration
//=> SpringBoot 가 자동으로 설정해줌을 의미 
//=> @EnableAutoConfiguration은 auto configuration 기능을 사용하겠다는 설정
//   @EnableAutoConfiguration을 설정하지 않으면 auto configuration 을 사용할 수 없음.
//=> SpringBoot 에는 다양한 Auto Configuration 이 있으며, 
//   @EnableWebSecurity 도 Security 자동설정을 사용한다는 의미임.

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // ** filter 등록
    // => 스프링 시큐리티에 jwtAuthenticationFilter를 사용하도록 알려줌
    // => 매 리퀘스트마다 CorsFilter 실행한 이후에 jwtAuthenticationFilter 실행하도록 설정.
    //    이 순서가 필수는 아니지만 적절하기때문에 이렇게 설정함.
    http.addFilterAfter(
        jwtAuthenticationFilter,
        CorsFilter.class);

    // ** http 시큐리티 빌더
    return http.httpBasic().disable() // token을 사용하므로 basic 인증 disable
        //.formLogin().disable()
        .csrf().disable() // csrf는 현재 사용하지 않으므로 disable
        .cors().and() // // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정.
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        // => session 기반이 아님을 선언
        .authorizeRequests().antMatchers("/", "/auth/**", "/resources/**", "/home", "/users/**","/item", "/notices/**", "/qnas/**", "/reviews/**").permitAll()
        // => "/", "/auth/**", "/home", "/member/**" 경로는 인증 안해도 됨.
        .anyRequest().authenticated().and()
        // => "/", "/auth/**", "/home", "/member/**"  이외의 모든 경로는 인증 해야됨.
        .build();
  } //filterChain

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }


} //class
