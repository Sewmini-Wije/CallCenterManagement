package com.call_center.Call.Center.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private String customerName;
    private String userContactNo;
    private String inqDescription;
    private String inqStatus;
    private Boolean adminRv;
    private Date createdAt;
    private Date updatedAt;
    private EmployeeDTO assignedSkilledAgent;
    private InquiryDTO assignedTicket;

}
