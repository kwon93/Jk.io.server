package io.jk.api.repository;

import io.jk.api.controller.dto.LoginRequest;
import io.jk.api.domain.Member;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final SqlSessionTemplate template;

    public int signup(Member member) {
        return template.insert("Member-mapper.signupMember", member);
    }

    public Member findByEmail(String email) {
        return template.selectOne("Member-mapper.findByEmail", email);
    }

    public int createSession(Member memberByEmail, String sessionId) {
        return template.insert("Member-mapper.createSession", memberByEmail);
    }
}
