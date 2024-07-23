package com.call_center.Call.Center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "EMPLOYEE_FIRST_NAME")
    private String employeeFirstName;
    @Column(name = "EMPLOYEE_LAST_NAME")
    private String employeeLastName;
    @Column(name = "EMPLOYEE_GENDER")
    private String employeeGender;
    @Column(name = "EMPLOYEE_POSITION")
    private String employeePosition;
    @Column(name = "EMPLOYEE_CONTACT_NO")
    private String employeeContactNo;
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;
    @Column(name = "EMPLOYEE_PASSWORD")
    private String employeePassword;
    @Column(nullable = false, updatable = false, name = "EMPLOYEE_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date employeeCreatedAt;
    @Column(nullable = false, name = "EMPLOYEE_UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date employeeUpdatedAt;

    @OneToMany(mappedBy = "id")
    private List<Ticket> assignedTickets;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "skilled_agent_specialization",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name = "inquiry_id"))
    private List<Inquiry> inquiryList = new ArrayList<>();
}
