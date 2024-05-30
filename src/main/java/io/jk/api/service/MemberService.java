package io.jk.api.service;

import io.jk.api.controller.dto.LoginRequest;
import io.jk.api.controller.dto.MemberSignUpRequest;
import io.jk.api.domain.Member;
import io.jk.api.domain.Session;
import io.jk.api.exception.InvalidPasswordException;
import io.jk.api.exception.LoginFailException;
import io.jk.api.exception.SignupFailException;
import io.jk.api.repository.MemberRepository;
import io.jk.api.repository.SessionRepository;
import io.jk.api.service.dto.MemberSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailService userDetailService;


    @Transactional
    public MemberSignUpResponse signup(MemberSignUpRequest request) {
        String encodedPassword
                = passwordEncoder.encode(request.getPassword());

        int signup = memberRepository.signup(Member.of(request, encodedPassword));
        if (signup == 0){
            throw new SignupFailException();
        }

        Member signupMember = findByEmail(request.getEmail());
        return new MemberSignUpResponse(signupMember);
    }

    private Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public String login(LoginRequest loginRequest) {
        Member memberByEmail = memberRepository.findByEmail(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getPassword(), memberByEmail.getPassword())) {
            throw new InvalidPasswordException();
        }

        int isLogin = sessionProcess(loginRequest, memberByEmail);
        if (isLogin != 1){
            throw new LoginFailException();
        }
        Session session = findSessionByMemberEmail(loginRequest);

        return session.getSessionId();
    }

    private int sessionProcess(LoginRequest loginRequest, Member memberByEmail) {
        String  uuid = UUID.randomUUID().toString();
        Session session = Session.of(loginRequest, memberByEmail.getMemberId(), uuid);
        return sessionRepository.createSession(session);
    }

    private Session findSessionByMemberEmail(LoginRequest request){
       return sessionRepository.findByMemberEmail(request.getEmail());
    }

    @Transactional
    public void logout(String sessionId) {
        sessionRepository.deleteSession(sessionId);
    }
}
