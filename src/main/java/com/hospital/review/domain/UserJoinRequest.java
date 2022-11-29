package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
    public User toEntity(String password){
        return User.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .build();
    }

}
