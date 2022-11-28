package com.hospital.review.service;

import com.hospital.review.Repository.UserRepository;
import com.hospital.review.domain.UserDto;
import com.hospital.review.domain.UserJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static UserRepository userRepository;

    public UserDto join(UserJoinRequest request) {
        return new UserDto("", "", "");
    }
}
