package io.jk.api.service.dto;

import io.jk.api.domain.Member;
import lombok.Getter;

@Getter
public class MemberSignUpResponse {

    private Long memberId;

    public MemberSignUpResponse(Member signupMember) {
        memberId = signupMember.getMemberId();
    }
}
