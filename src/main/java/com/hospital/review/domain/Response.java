package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode){
        return new Response(resultCode, null);
    }

    public static <T> Response<T> success(T result){
        return new Response("SUCCESS", result);
    }
}