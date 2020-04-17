package sanial.netheos.demoapi.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;


/**
 * This class allow using basic security implemented from SpringSecurity
 */
@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String ROLE_USER = "USER";
    private final String ROLE_ADMIN = "ADMIN";

    @Autowired
    private Environment env;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //Add roles usage in memory
        auth.inMemoryAuthentication()
                .withUser(env.getProperty("api.user.login")).password(encoder.encode(env.getProperty("api.user.password"))).roles(ROLE_USER)
                .and()
                .withUser(env.getProperty("api.admin.login")).password(encoder.encode(env.getProperty("api.admin.password"))).roles(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("Demo API Netheos");

        //Add restriction roles on specific URL
        http.csrf().disable().antMatcher("/api/**").authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/faqtag/create").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST,"/api/faq/search").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.GET,"/api/faq").hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(entryPoint);
    }
}

