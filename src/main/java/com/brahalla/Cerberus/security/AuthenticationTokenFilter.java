package com.brahalla.Cerberus.security;

import com.brahalla.Cerberus.service.impl.UserDetailsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger log = Logger.getLogger(getClass());

    @Value("${cerberus.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(authToken);
        if  (username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
            	unsuccessfulAuthentication((HttpServletRequest)request, (HttpServletResponse)response, new UsernameNotFoundException("User name does not match token"));
            	return;
            } 
        }
        if (username==null && !httpRequest.getServletPath().equals("/auth/login")) {
        	unsuccessfulAuthentication((HttpServletRequest)request, (HttpServletResponse)response, new UsernameNotFoundException("no user name"));
        	return;
        }

        chain.doFilter(request, response);
    }

}
