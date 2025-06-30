package com.UserSignupPipeline.UserSignupPipeline.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupDTO {
    private String name;
    private String email;
    private String phoneNumber; // Optional
}