package com.employee.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(
        name = "employee_details",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "Email"),  // Ensures unique emails
                @UniqueConstraint(columnNames = "Phone")   // Ensures unique phone numbers
        }
)
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Emp_Id")
    private long empId;

    @Column(name = "Name")
    private String name;


    @Column(name = "Email",unique = true)
    private String email;

    @Column(name = "Department")
    private String department;

    @Column(name = "Phone",unique = true)
    private String phone;

    @Column(name = "Password")
    private String password;

    // One employee can have multiple tasks
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TaskDetails> tasks;
}
