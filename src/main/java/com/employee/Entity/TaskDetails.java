package com.employee.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Task_Details")
public class TaskDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Task_No")
    private long taskNo;

    private String taskTitle;

    @Column(name = "timeStamp", nullable = false, updatable = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Many tasks can belong to one employee
    @ManyToOne
    @JoinColumn(name = "Emp_Id", nullable = false)  // Ensure it matches EmployeeDetails
    private EmployeeDetails employee;

    private String category;


}
