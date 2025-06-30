package com.UserSignupPipeline.UserSignupPipeline.service;

import com.UserSignupPipeline.UserSignupPipeline.dao.UserSignupRepository;
import com.UserSignupPipeline.UserSignupPipeline.dto.UserSignupDTO;
import com.UserSignupPipeline.UserSignupPipeline.model.UserSignup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSignupService {

    private final UserSignupRepository repository;

    public UserSignupService(UserSignupRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserSignupDTO save(UserSignupDTO dto) {

        if(null == dto.getEmail() || dto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is invalid");
        }

//        validate if email already exists --> (true) then update existing data else save new data
        UserSignup userSignup = repository.findByEmail(dto.getEmail()).map(
                existingUser -> {
                    existingUser.setName(dto.getName());
                    existingUser.setPhoneNumber(dto.getPhoneNumber());
                    return repository.save(existingUser);
                }).orElseGet( () -> {
            UserSignup entity = UserSignup.builder()
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .phoneNumber(dto.getPhoneNumber())
                    .build();
            return repository.save(entity);
                });


        return new UserSignupDTO(
                userSignup.getName(),
                userSignup.getEmail(),
                userSignup.getPhoneNumber()
        );


    }
}
