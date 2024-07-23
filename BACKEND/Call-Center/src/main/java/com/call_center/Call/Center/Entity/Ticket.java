package com.call_center.Call.Center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Long id;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_CONTACT_NO")
    private String userContactNo;
    @Column(name = "TICKET_DESCRIPTION")
    private String inqDescription;
    @Column(name = "TICKET_STATUS")
    private String inqStatus;
    @Column(name = "ADMIN_REVIEW_CHECKED")
    private Boolean adminRv = false;
    @Column(nullable = false, updatable = false, name = "CREATE_DATE_&_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Column(nullable = false, name = "UPDATE_DATE_&_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedSkilledAgent;

    @ManyToOne
    @JoinColumn(name = "inqId")
    private Inquiry assignedTicket;
}
