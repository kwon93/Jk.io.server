package io.jk.api.domain;

import io.jk.api.controller.Role;
import io.jk.api.controller.dto.MemberSignUpRequest;
import io.jk.api.repository.MemberRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {

    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private Role role;

    @Builder
    public Member(Long memberId, String email, String nickName, String password, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.role = role;
    }

    public static Member of(MemberSignUpRequest dto, String encodedPassword){
        return Member.builder()
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .password(encodedPassword)
                .role(dto.getRole())
                .build();
    }
}
