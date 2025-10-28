package com.example.rate_limiting_demo.repository;

import com.example.rate_limiting_demo.model.User;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@NullMarked
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Page<User> findAll(Pageable pageable);
}
