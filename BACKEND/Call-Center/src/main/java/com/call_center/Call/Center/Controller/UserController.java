package com.call_center.Call.Center.Controller;

import com.call_center.Call.Center.DTO.EmployeeDTO;
import com.call_center.Call.Center.DTO.UserDTO;
import com.call_center.Call.Center.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> findAllUser() {
        return userService.getAllUser();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search/{userContactNo}")
    public ResponseEntity<List<UserDTO>> getNameByContactNo(@PathVariable long userContactNo) {
        return userService.getUserByContactNo(userContactNo);
    }
}
