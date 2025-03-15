package com.employee.Repository;

import com.employee.Entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetails,Long> {
    EmployeeDetails getEmployeeDetailsByPhone(String phone);
    EmployeeDetails getEmployeeDetailsByEmpId(long empId);
    EmployeeDetails getEmployeeDetailsByEmail(String email);
}
