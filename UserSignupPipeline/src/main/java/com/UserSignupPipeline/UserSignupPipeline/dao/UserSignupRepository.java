package com.UserSignupPipeline.UserSignupPipeline.dao;

import com.UserSignupPipeline.UserSignupPipeline.model.UserSignup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSignupRepository extends JpaRepository<UserSignup, Long> {

    Optional<UserSignup> findByEmail(String email);
}
