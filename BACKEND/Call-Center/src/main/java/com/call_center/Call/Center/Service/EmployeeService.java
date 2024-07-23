package com.call_center.Call.Center.Service;

import com.call_center.Call.Center.DTO.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO);
    ResponseEntity<List<EmployeeDTO>> getAllEmployee();
    ResponseEntity<EmployeeDTO> updateEmployee(long employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(long employeeId);
    ResponseEntity<List<EmployeeDTO>> getEmployeeByPosition(String employeePosition);
    ResponseEntity<List<EmployeeDTO>> getEmployeeByFirstName(String employeeFirstName);
    ResponseEntity<List<EmployeeDTO>> getEmployeeByLastName(String employeeLastName);
    ResponseEntity<EmployeeDTO> getEmployeeById(long id);
    ResponseEntity<EmployeeDTO> getEmployeeByEmailAndPassword(String employeeEmail, String employeePassword);
    boolean existByEmail(String employeeEmail);
    void assignSpecialization(long employeeId, long inqId);
    ResponseEntity<List<EmployeeDTO>> getEmployeeByInquiryId(long inqId);
}
