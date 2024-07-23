package com.call_center.Call.Center.Service.Impl;

import com.call_center.Call.Center.DTO.UserDTO;
import com.call_center.Call.Center.Entity.User;
import com.call_center.Call.Center.Repository.UserRepository;
import com.call_center.Call.Center.Service.UserService;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    UserRepository userRepo;

    @Transactional
    @Override
    public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
        try {
            User user = DTOToEntity(userDTO);

            User saveUser = userRepo.save(user);
            return ResponseEntity.ok(EntityToDTO(saveUser));
        } catch (Exception e) {
            log.error("Error : " +e);
            return null;
        }
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<User> getUser = userRepo.findAll();
        List<UserDTO> dto = new ArrayList<>();
        for(User user: getUser) {
            dto.add(EntityToDTO(user));
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(long id, UserDTO userdto) {
        User user = DTOToEntity(userdto);

        if(userRepo.existsById(id)) {
            user.setUserId(id);
            User saveUser = userRepo.save(user);

            EntityToDTO(saveUser);
            return ResponseEntity.ok(userdto);
        } else
            return null;
    }

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUserByContactNo(long userContactNo) {
        List<User> getUser = userRepo.findByUserContactNo(userContactNo);
        List<UserDTO> dto = new ArrayList<>();
        if(getUser != null) {
            for(User user: getUser) {
                dto.add(EntityToDTO(user));
            }
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    private User DTOToEntity(UserDTO userDTO)
    {
        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(userDTO.getUserLastName());
        user.setUserGender(userDTO.getUserGender());
        user.setUserContactNo(userDTO.getUserContactNo());
        user.setUserAddress(userDTO.getUserAddress());
        user.setUserDateOfBirth(userDTO.getUserDateOfBirth());
        return user;
    }

    private UserDTO EntityToDTO(User user)
    {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setUserGender(user.getUserGender());
        userDTO.setUserContactNo(user.getUserContactNo());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setUserDateOfBirth(user.getUserDateOfBirth());
        userDTO.setUserCreatedAt(user.getUserCreatedAt());
        userDTO.setUserUpdatedAt(user.getUserUpdatedAt());

        return userDTO;
    }
}
