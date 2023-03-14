package com.example.springboardlv3.controller;



import com.example.springboardlv3.dto.LoginRequestDto;
import com.example.springboardlv3.dto.ResponseDto;
import com.example.springboardlv3.dto.SignupRequestDto;
import com.example.springboardlv3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

//    // 회원 조회
//    @GetMapping("/signup")
//    public ModelAndView signupPage() {return  new ModelAndView("signup");} // ModelAndView : Controller의 처리 결과를 보여줄 view와 view에서 사용할 값을 전달하는 클래스이다.

//    @GetMapping("/login")
//    public ModelAndView loginPage() {return  new ModelAndView("login");}

    // 회원 가입
    @PostMapping("/signup")
    public ResponseDto singup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new ResponseDto("회원가입 성공", HttpStatus.OK.value());
    }

    // 로그인
    @PostMapping("/login") // 로그인인데 post하는 이유가 궁금합니다.  HttpServletrequest : 사용자 정보(토큰)를 가져오기 위해 사용
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) { // HttpServletResponse response : 요청을 보낸 클라이언트에게 응답을 보내기 위한 객체를 생성해서 서블릿에게 전달. 서블릿은 해당 객체를 이용하여 content type, 응답코드, 응답 메시지 등을 전송한다. 쿠키를 보내기 위해서 사용
        userService.login(loginRequestDto, response);
        return new ResponseDto("로그인 성공", HttpStatus.OK.value());
//      return new ResponseDto("로그인 성공", HttpStatus.OK.value()); 이 방법은 무엇인가? 만약에 반환타입을 Response로 해서 이방법을 쓰면 장점이 무엇이 있을까...? 메세지 반환 + http?
    }


}
