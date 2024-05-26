package io.jk.api.controller.dto;

import io.jk.api.controller.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {

    private String email;
    private String nickName;
    private String password;
    private Role role;
}
