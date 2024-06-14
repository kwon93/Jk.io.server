package io.jk.api.controller;


import io.jk.api.controller.dto.LoginRequest;
import io.jk.api.controller.dto.MemberSignUpRequest;
import io.jk.api.service.MemberService;
import io.jk.api.service.dto.MemberSignUpResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody  MemberSignUpRequest request){
        MemberSignUpResponse member = memberService.signup(request);
        return ResponseEntity.ok(member.getMemberId());
    }

    @PostMapping("/signin")
    public ResponseEntity<String > login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        String sessionId = memberService.login(loginRequest);
        cookieProcessing(response, sessionId);

        return ResponseEntity
                .ok()
                .build();
    }

    private void cookieProcessing(HttpServletResponse response, String sessionId) {
        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(300000);
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

    @PostMapping("/userLogout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String sessionId = cookies[0].getValue();

        memberService.logout(sessionId);

        cookies[0].setMaxAge(0);
        response.addCookie(cookies[0]);

        return ResponseEntity.noContent().build();

    }

    private ResponseCookie getCookie(String sessionId) {
        return ResponseCookie.from("SESSION", sessionId)
                .path("/")
                .domain("localhost")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();
    }

    @PostMapping("sessionTest")
    private int redisSessionTest(HttpSession session) {
        int num = 123;
        session.setAttribute("num", num);
        return num;
    }

}
