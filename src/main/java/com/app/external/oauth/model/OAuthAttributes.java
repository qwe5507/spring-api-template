package com.app.external.oauth.model;

import com.app.domain.member.entity.Member;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// 소셜 로그인에서 받은 다른 응답들의 형태를 통합
@ToString
@Getter @Builder
public class OAuthAttributes {
    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
                .profile(profile)
                .role(role)
                .build();
    }
}
