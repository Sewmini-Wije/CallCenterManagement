package com.call_center.Call.Center.DTO;

import com.call_center.Call.Center.Entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeGender;
    private String employeePosition;
    private String employeeContactNo;
    private String employeeEmail;
    private String employeePassword;
    private Date employeeCreatedAt;
    private Date employeeUpdatedAt;
    private List<InquiryDTO> inquiryList;
}
