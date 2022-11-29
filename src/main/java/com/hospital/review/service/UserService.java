package com.hospital.review.service;

import com.hospital.review.Repository.UserRepository;
import com.hospital.review.domain.User;
import com.hospital.review.domain.UserDto;
import com.hospital.review.domain.UserJoinRequest;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewAppException;
import com.hospital.review.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60; // 1시간

    public String login(String userName, String password) {

        // userName있는지 여부 확인
        // 없으면 NOT_FOUND에러 발생
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)));

        // password일치 하는지 여부 확인
        if(!encoder.matches(password, user.getPassword())){
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, String.format("userName 또는 password가 잘못 됐습니다."));
        }

        // 두가지 확인중 예외 안났으면 Token발행
        return JwtTokenUtil.createToken(userName, secretKey, expireTimeMs);
    }


    public UserDto join(UserJoinRequest request){
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user->{
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, "");
                });
        User user=userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }



}
