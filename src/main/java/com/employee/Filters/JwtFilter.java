package com.employee.Filters;

import com.employee.Entity.EmployeeDetails;
import com.employee.Service.EmployeeDetailService;
import com.employee.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private  final JWTService jwtService;

    private final EmployeeDetailService employeeDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        //Do not assign it as "" as it cant be change after that
        String extractedToken =null;
        String username = null;

        if(authHeader!= null && authHeader.startsWith("Bearer ")){
            extractedToken = authHeader.substring(7);
            username = jwtService.findUsernameByToken(extractedToken);
            System.out.println("This is the username"+username);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
            UserDetails userDetails = employeeDetailService.loadUserByUsername(username);
            System.out.println(userDetails);
            if(jwtService.validateToken(extractedToken, userDetails)){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null ,userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request,response);
    }
}
