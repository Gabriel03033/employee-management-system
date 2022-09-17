package com.project.it.employee;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   /* @Query("SELECT employee FROM Employee employee WHERE employee.name LIKE CONCAT('%', :name, '%')")
    List<Employee> searchEmployeesByName(String name);*/

    Page<Employee> findByNameContaining(String searchedName, Pageable pageable);
}
