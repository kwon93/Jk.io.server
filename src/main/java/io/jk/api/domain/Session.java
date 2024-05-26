package io.jk.api.domain;

import io.jk.api.controller.dto.LoginRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Session {

    private String sessionId;
    private Long memberId;
    private String memberEmail;
    private LocalDateTime createdAt;

    @Builder
    public Session(String sessionId, Long memberId, String memberEmail, LocalDateTime createdAt) {
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.createdAt = createdAt;
    }

    public static Session of(LoginRequest dto, Long memberId, String uuid){
        return Session.builder()
                .sessionId(uuid)
                .memberId(memberId)
                .memberEmail(dto.getEmail())
                .build();
    }
}
