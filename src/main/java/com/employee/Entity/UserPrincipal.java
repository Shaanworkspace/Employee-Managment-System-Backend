package com.employee.Entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final EmployeeDetails employeeDetails;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = employeeDetails.getRole();

        // Ensure the role has "ROLE_" prefix (Spring Security convention)
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role.toUpperCase();
        }

        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
    public String getRole(){
        return employeeDetails.getRole();
    }

    @Override
    public String getPassword() {
        return employeeDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeDetails.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
