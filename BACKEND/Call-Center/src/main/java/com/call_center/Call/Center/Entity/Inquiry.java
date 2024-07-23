package com.call_center.Call.Center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INQUIRY_ID")
    private Long inqId;
    @Column(name = "INQUIRY_TYPE")
    private String inqType;

    @ManyToMany(mappedBy = "inquiryList")
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "assignedTicket")
    private List<Ticket> ticketList;
}
