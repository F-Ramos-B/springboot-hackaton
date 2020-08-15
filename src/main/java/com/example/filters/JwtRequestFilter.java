package com.example.filters;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.service.PessoaService;
import com.example.utils.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String authHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if (nonNull(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
			jwt = StringUtils.removeStart(authHeader, BEARER_PREFIX);
			username = jwtUtil.extractUsername(jwt);
		}
		
		if (nonNull(username) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
			UserDetails userDetails = pessoaService.loadUserByUsername(username);
			
			if (jwtUtil.validateToken(jwt, userDetails).booleanValue()) {
				validateToken(request, userDetails);
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

	private void validateToken(HttpServletRequest request, UserDetails userDetails) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
		token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(token);
	}
	
}
