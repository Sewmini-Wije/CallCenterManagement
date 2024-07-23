package com.call_center.Call.Center.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Column(name = "USER_GENDER")
    private String userGender;
    @Column(name = "USER_CONTACT_NO")
    private Long userContactNo;
    @Column(name = "USER_ADDRESS")
    private String userAddress;
    @Column(name = "USER_DATE_OF_BIRTH")
    private Date userDateOfBirth;
    @Column(nullable = false, updatable = false, name = "USER_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date userCreatedAt;
    @Column(nullable = false, name = "USER_UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date userUpdatedAt;
}
