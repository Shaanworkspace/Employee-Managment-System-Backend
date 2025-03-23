package com.employee.Service;

import com.employee.Entity.EmployeeDetails;
import com.employee.Repository.EmployeeDetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService {
    private final EmployeeDetailRepo employeeDetailRepo;

    public EmployeeDetails AddEmployee(EmployeeDetails employeeDetails){
        return employeeDetailRepo.save(employeeDetails);
    }

    public List<EmployeeDetails> getEmployee(){
        return employeeDetailRepo.findAll();
    }

}
