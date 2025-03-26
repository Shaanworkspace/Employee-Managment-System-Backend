package com.employee.Service;

import com.employee.Entity.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public String verify(EmployeeDetails employeeDetails) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employeeDetails.getEmail(), employeeDetails.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateKey(employeeDetails.getEmail());
        } else {
            return "Fail to login";
        }
    }
}