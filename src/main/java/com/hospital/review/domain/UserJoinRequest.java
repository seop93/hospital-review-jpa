package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;
}
