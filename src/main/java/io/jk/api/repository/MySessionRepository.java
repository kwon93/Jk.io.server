package io.jk.api.repository;

import io.jk.api.domain.Session;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MySessionRepository {

    private final SqlSessionTemplate template;

    public int createSession(Session session) {
        return template.insert("Session-mapper.createSession", session);
    }

    public Session findByMemberEmail(String email) {
        return template.selectOne("Session-mapper.findByMemberEmail", email);
    }

    public Session findById(String sessionId){
        return template.selectOne("Session-mapper.findById", sessionId);
    }

    public int deleteSession(String sessionId) {
        return template.delete("Session-mapper.deleteSession", sessionId);
    }
}
