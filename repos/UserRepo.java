package com.artemiishabanov.boxes.repos;

import com.artemiishabanov.boxes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
