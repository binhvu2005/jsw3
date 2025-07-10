package com.example.ss3.repository;

import com.example.ss3.dto.EmployeeDTO;
import com.example.ss3.entity.Employee;
import com.example.ss3.entity.EmployeeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findBySalaryGreaterThan(Double salary);
    Page<Employee> findAll(Pageable pageable);
    @Query("SELECT new com.example.ss3.dto.EmployeeDTO(e.id, e.name, e.email, e.salary) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTO();
    @Query("SELECT e.name AS name, e.phoneNumber AS phoneNumber, e.salary AS salary FROM Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();
}
