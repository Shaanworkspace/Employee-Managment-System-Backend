package com.employee.Controller;

import com.employee.Entity.EmployeeDetails;
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

    //Add an Employee
    @PostMapping("/add")
    public EmployeeDetails addEmployee(@RequestBody EmployeeDetails employeeDetails){
        return employeeDetailService.AddEmployee(employeeDetails);
    }

    @GetMapping("/get")
    public List<EmployeeDetails> getEmployee(){
        return employeeDetailService.getEmployee();
    }


}
