package com.call_center.Call.Center.Repository;

import com.call_center.Call.Center.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeePosition(String employeePosition);
    List<Employee> findByEmployeeFirstName(String employeeFirstName);
    List<Employee> findByEmployeeLastName(String employeeLastName);
    Employee findByEmployeeEmailAndEmployeePassword(String employeeEmail, String employeePassword);
    boolean existsByEmployeeEmail(String employeeEmail);
    @Query("SELECT e from Employee e JOIN e.inquiryList i WHERE i.inqId = :inqId")
    List<Employee> findByInqId(@Param("inqId") Long inqId);
}
