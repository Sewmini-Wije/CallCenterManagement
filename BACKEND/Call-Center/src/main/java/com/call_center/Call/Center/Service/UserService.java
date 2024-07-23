package com.call_center.Call.Center.Service;

import com.call_center.Call.Center.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDTO> addUser(UserDTO userDTO);
    ResponseEntity<List<UserDTO>> getAllUser();
    ResponseEntity<UserDTO> updateUser(long userId, UserDTO userdto);
    void deleteUser(long userId);
    ResponseEntity<List<UserDTO>> getUserByContactNo(long userContactNo);
}
