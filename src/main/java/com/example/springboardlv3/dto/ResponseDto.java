package com.example.springboardlv3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor // NoArgsConstructor 생성자를 만들기 위해 사용하는 어노테이션
public class ResponseDto {

    private int statusCode; // enum 형식으로
    private String msg;

    public  ResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

//    enum 방식으로 한번 알아보자~
//    private String statusCode; 내가 한 방식
}
