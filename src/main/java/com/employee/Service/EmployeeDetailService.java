package com.employee.Service;

import com.employee.Entity.EmployeeDetails;
import com.employee.Entity.UserPrincipal;
import com.employee.Repository.EmployeeDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService implements UserDetailsService {
    private final EmployeeDetailRepo employeeDetailRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public EmployeeDetails AddEmployee(EmployeeDetails employeeDetails){
        employeeDetails.setPassword(bCryptPasswordEncoder.encode(employeeDetails.getPassword()));
        return employeeDetailRepo.save(employeeDetails);
    }

    public List<EmployeeDetails> getEmployee(){
        return employeeDetailRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeDetails employeeDetails = employeeDetailRepo.getEmployeeDetailsByEmail(username);
        if(employeeDetails == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not found. Please Search with correct Credentials");
        }
        return new UserPrincipal(employeeDetails);
    }
}
