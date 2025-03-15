package com.employee.Service;

import com.employee.Entity.EmployeeDetails;
import com.employee.Repository.EmployeeDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService {
    private final EmployeeDetailRepo employeeDetailRepo;

    public EmployeeDetails AddEmployee(EmployeeDetails employeeDetails){
        return employeeDetailRepo.save(employeeDetails);
    }

}
