package com.call_center.Call.Center.Repository;

import com.call_center.Call.Center.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserContactNo(long userContactNo);
}
