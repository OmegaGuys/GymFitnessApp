package uz.pdp.gymfitnessapp.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.gymfitnessapp.common.AppConstants;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
       try {
           String header = request.getHeader(HttpHeaders.AUTHORIZATION);

           if (StringUtils.isBlank(header) || !header.startsWith(AppConstants.TOKEN_TYPE)){
               filterChain.doFilter(request,response);
               return;
           }
           String token = header.substring(7);
           Claims claims = jwtTokenProvider.claims(token);
           String email = claims.getSubject();

           UserDetails userDetails = userDetailsService.loadUserByUsername(email);
           var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);

           filterChain.doFilter(request,response);

       } catch (Exception e){
           log.error(e.getMessage(),e);
           filterChain.doFilter(request,response);
       }
    }
}
