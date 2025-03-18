package com.employee.Controller;

import com.employee.Entity.EmployeeDetails;
import com.employee.Service.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor //Will take only private final or @NONNull
public class EmployeeDetailsController {

    private final EmployeeDetailService employeeDetailService;

    //Add an Employee
    @PostMapping("/add")
    public EmployeeDetails addEmployee(@RequestBody EmployeeDetails employeeDetails){
        return employeeDetailService.AddEmployee(employeeDetails);
    }


}
