package com.call_center.Call.Center.Controller;

import com.call_center.Call.Center.DTO.EmployeeDTO;
import com.call_center.Call.Center.DTO.TicketDTO;
import com.call_center.Call.Center.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Employee")
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/{employeePosition}")
    public ResponseEntity<List<EmployeeDTO>> getAgent(@PathVariable String employeePosition) {
        return  employeeService.getEmployeeByPosition(employeePosition);
    }

    @GetMapping("search/{employeeFirstName}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByFirstName(@PathVariable String employeeFirstName) {
        return  employeeService.getEmployeeByFirstName(employeeFirstName);
    }

    @GetMapping("search/{employeeLastName}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByLastName(@PathVariable String employeeLastName) {
        return  employeeService.getEmployeeByLastName(employeeLastName);
    }


    @GetMapping("/findById/{employeeId}")
    public ResponseEntity<EmployeeDTO> findById (@PathVariable long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/search")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmailAndPassword(@RequestParam String employeeEmail, @RequestParam String employeePassword) {
        return  employeeService.getEmployeeByEmailAndPassword(employeeEmail, employeePassword);
    }

    @GetMapping("/exist")
    public ResponseEntity<Boolean> checkEmployeeExist(@RequestParam String employeeEmail) {
        boolean exist = employeeService.existByEmail(employeeEmail);
        return ResponseEntity.ok(exist);
    }

    @PostMapping("{employeeId}/assign/{inqId}")
    public void assignSpecialization(@PathVariable long employeeId, @PathVariable long inqId) {
        employeeService.assignSpecialization(employeeId, inqId);
    }

    @GetMapping("/searchInq/{inqId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByInquiryId(@PathVariable long inqId) {
        return  employeeService.getEmployeeByInquiryId(inqId);
    }
}