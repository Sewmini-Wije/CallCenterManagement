package com.call_center.Call.Center.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private Long userContactNo;
    private String userAddress;
    private Date userDateOfBirth;
    private  Date userCreatedAt;
    private  Date userUpdatedAt;

}
