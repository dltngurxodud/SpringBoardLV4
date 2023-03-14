package com.example.springboardlv3.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ApiException extends RuntimeException{ // Dto 같은것. RuntimeException : 자바에 포함된 패키지, JVM(자바 가상 실행 머신)이 작동중에 예외의 슈퍼 클래스

    private final ExceptionEnum errorCode; // 변수이름은 어디다가 쓰는거지?

}
