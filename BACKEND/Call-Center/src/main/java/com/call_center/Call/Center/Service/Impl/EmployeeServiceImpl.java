package com.call_center.Call.Center.Service.Impl;

import com.call_center.Call.Center.DTO.EmployeeDTO;
import com.call_center.Call.Center.DTO.InquiryDTO;
import com.call_center.Call.Center.Entity.Employee;
import com.call_center.Call.Center.Entity.Inquiry;
import com.call_center.Call.Center.Repository.EmployeeRepository;
import com.call_center.Call.Center.Repository.InquiryRepository;
import com.call_center.Call.Center.Service.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Log log = LogFactory.getLog(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    InquiryRepository inqRepo;


    @Override
    public ResponseEntity<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO) {
        try {
            System.out.println(employeeDTO);
            Employee employee = DTOToEntity(employeeDTO);

            System.out.println(employee);
            Employee saveEmployee = employeeRepo.save(employee);
            return ResponseEntity.ok(EntityToDTO(saveEmployee));
        } catch (Exception e) {
            log.error("Error : " +e);
            return null;
        }
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<Employee> getEmployee = employeeRepo.findAll();
        List<EmployeeDTO> dto = new ArrayList<>();
        for(Employee employee : getEmployee) {
            dto.add(EntityToDTO(employee));
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<EmployeeDTO> updateEmployee(long employeeId, EmployeeDTO employeeDTO) {
        Employee employee = DTOToEntity(employeeDTO);

        if(employeeRepo.existsById(employeeId)) {
            employee.setEmployeeId(employeeId);
            Employee saveEmployee = employeeRepo.save(employee);

            EntityToDTO(saveEmployee);
            return ResponseEntity.ok(employeeDTO);
        } else
            return null;
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByPosition(String employeePosition) {
        List<Employee> employees = employeeRepo.findByEmployeePosition(employeePosition);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOS.add(EntityToDTO(employee));
        }
        return  ResponseEntity.ok(employeeDTOS);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByFirstName(String employeeFirstName) {
        List<Employee> employees = employeeRepo.findByEmployeeFirstName(employeeFirstName);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOS.add(EntityToDTO(employee));
        }
        return  ResponseEntity.ok(employeeDTOS);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByLastName(String employeeLastName) {
        List<Employee> employees = employeeRepo.findByEmployeeLastName(employeeLastName);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOS.add(EntityToDTO(employee));
        }
        return  ResponseEntity.ok(employeeDTOS);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(long employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if(employee.isPresent()) {
            EmployeeDTO dto = EntityToDTO(employee.get());
            return ResponseEntity.ok(dto);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeByEmailAndPassword(String employeeEmail, String employeePassword) {
        Employee foundEmployee = employeeRepo.findByEmployeeEmailAndEmployeePassword(employeeEmail, employeePassword);

        if(foundEmployee != null) {
            return ResponseEntity.ok(EntityToDTO(foundEmployee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public boolean existByEmail(String employeeEmail) {
        return employeeRepo.existsByEmployeeEmail(employeeEmail);
    }

    @Override
    public void assignSpecialization(long employeeId, long inqId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        Inquiry inquiry = inqRepo.findById(inqId)
                .orElseThrow(() -> new NoSuchElementException("Inquiry not found"));
        employee.getInquiryList().add(inquiry);
        employeeRepo.save(employee);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByInquiryId(long inqId) {
        List<Employee> employees = employeeRepo.findByInqId(inqId);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOS.add(EntityToDTO(employee));
        }
        return  ResponseEntity.ok(employeeDTOS);
    }

    private Employee DTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setEmployeeFirstName(employeeDTO.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeDTO.getEmployeeLastName());
        employee.setEmployeeGender(employeeDTO.getEmployeeGender());
        employee.setEmployeePosition(employeeDTO.getEmployeePosition());
        employee.setEmployeeContactNo(employeeDTO.getEmployeeContactNo());
        employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
        employee.setEmployeePassword(employeeDTO.getEmployeePassword());

        List<Inquiry> managedInquiries = new ArrayList<>();
        for (InquiryDTO inquiryDTO : employeeDTO.getInquiryList()) {
            Inquiry managedInquiry = inqRepo.findById(inquiryDTO.getInqId())
                    .orElseThrow(() -> new NoSuchElementException("Inquiry not found"));
            managedInquiries.add(managedInquiry);
        }
        employee.setInquiryList(managedInquiries);

        return employee;
    }


    private EmployeeDTO EntityToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeFirstName(employee.getEmployeeFirstName());
        employeeDTO.setEmployeeLastName(employee.getEmployeeLastName());
        employeeDTO.setEmployeeGender(employee.getEmployeeGender());
        employeeDTO.setEmployeePosition(employee.getEmployeePosition());
        employeeDTO.setEmployeeContactNo(employee.getEmployeeContactNo());
        employeeDTO.setEmployeeEmail(employee.getEmployeeEmail());
        employeeDTO.setEmployeePassword(employee.getEmployeePassword());
        employeeDTO.setEmployeeCreatedAt(employee.getEmployeeCreatedAt());
        employeeDTO.setEmployeeUpdatedAt(employee.getEmployeeUpdatedAt());

        List<InquiryDTO> inquiryDTOList = new ArrayList<>();
        for (Inquiry inquiry : employee.getInquiryList()) {
            InquiryDTO inquiryDTO = new InquiryDTO();
            inquiryDTO.setInqId(inquiry.getInqId());
            inquiryDTO.setInqType(inquiry.getInqType());

            inquiryDTOList.add(inquiryDTO);
        }
        employeeDTO.setInquiryList(inquiryDTOList);

        return employeeDTO;
    }
}
