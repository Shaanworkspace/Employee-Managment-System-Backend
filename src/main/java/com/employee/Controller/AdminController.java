package com.employee.Controller;

import com.employee.Entity.EmployeeDetails;
import com.employee.Repository.EmployeeDetailRepo;
import com.employee.Service.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeDetailService employeeDetailService;
    private final EmployeeDetailRepo employeeDetailRepo;

    @GetMapping("/all-emp")
    public ResponseEntity<List<EmployeeDetails>> getAllEmployee (){
        List<EmployeeDetails> listOfEmployee = employeeDetailRepo.findAll();
        if (!listOfEmployee.isEmpty()){
            return new ResponseEntity<>(listOfEmployee, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
