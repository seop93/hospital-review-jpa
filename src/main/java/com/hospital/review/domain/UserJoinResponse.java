package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserJoinResponse {
    private String userName;
    private String email;
}
