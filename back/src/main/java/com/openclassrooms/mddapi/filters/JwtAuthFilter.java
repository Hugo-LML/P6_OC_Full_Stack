package com.openclassrooms.mddapi.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassrooms.mddapi.services.JWTService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JWTService jwtService;
  private final UserDetailsService userDetailsService;

  // This method is called for each incoming HTTP request and is responsible for handling JWT-based authentication
  @Override
  protected void doFilterInternal(
    @NotNull HttpServletRequest request,
    @NotNull HttpServletResponse response,
    @NotNull FilterChain filterChain
  ) throws ServletException, IOException {
    
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;
    
    // If the Authorization header is missing or does not start with "Bearer ", move on to the next filter of the chain
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    // Extract the JWT token and user email from the Authorization header
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);

    // If the user email is not null and there is no existing authentication in the SecurityContextHolder
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      // Load user details from the userDetailsService using the extracted user email
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      // Validate the JWT token against the loaded user details
      if (jwtService.isTokenValid(jwt, userDetails)) {
        // Create an authentication token and set it in the SecurityContextHolder
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    // Move on to the next filter of the cain
    filterChain.doFilter(request, response);
  }

}
