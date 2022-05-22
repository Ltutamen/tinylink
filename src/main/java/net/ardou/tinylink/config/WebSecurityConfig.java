package net.ardou.tinylink.config;

import net.ardou.tinylink.admin.AdminPanelController;
import net.ardou.tinylink.auth.RegisterPage;
import net.ardou.tinylink.links.TinylinkController;
import net.ardou.tinylink.redirect.RedirectController;
import net.ardou.tinylink.user.Role;
import net.ardou.tinylink.user.UserDetailsService;
import net.ardou.tinylink.userswitch.UserSwitch;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_ENDPOINT = "/login";
    public static String INDEX_ENDPOINT = "/";
    static final String[] GUEST_ENDPOINTS = {INDEX_ENDPOINT, LOGIN_ENDPOINT, RegisterPage.REGISTER_ENDPOINT, RedirectController.REDIRECT_ENDPOINT};
    static final String[] USER_ENDPOINTS = {TinylinkController.LINKS_ENDPOINT};
    static final String[] ADMIN_ENDPOINTS = {AdminPanelController.ADMIN_PANEL_ENDPOINT};

    final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(GUEST_ENDPOINTS).permitAll()
                .antMatchers(USER_ENDPOINTS).hasAuthority(Role.USER.name())
                .antMatchers(ADMIN_ENDPOINTS).hasAuthority(Role.ADMIN.name())
                .and()
                .formLogin()
                .defaultSuccessUrl(UserSwitch.USER_SWITCH_ENDPOINT)
                .usernameParameter("mail")
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
}
