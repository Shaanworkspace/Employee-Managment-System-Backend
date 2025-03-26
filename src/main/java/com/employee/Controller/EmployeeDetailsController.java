package com.employee.Controller;

import com.employee.Entity.EmployeeDetails;
import com.employee.Service.AuthenticationService;
import com.employee.Service.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor //Will take only private final or @NONNull
public class EmployeeDetailsController {
    private final EmployeeDetailService employeeDetailService;
    private final AuthenticationService authenticationService;

    //Add an Employee
    @PostMapping("/register")
    public EmployeeDetails registerEmployee(@RequestBody EmployeeDetails employeeDetails){
        return employeeDetailService.AddEmployee(employeeDetails);
    }

    @PostMapping("/login")
    public String loginEmployee(@RequestBody EmployeeDetails employeeDetails){
        return authenticationService.verify(employeeDetails);
    }

    @GetMapping("/get")
    public List<EmployeeDetails> getEmployee(){
        return employeeDetailService.getEmployee();
    }


}
