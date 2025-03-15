package com.employee.Repository;

import com.employee.Entity.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDetailRepo extends JpaRepository<TaskDetails,Long> {

}

