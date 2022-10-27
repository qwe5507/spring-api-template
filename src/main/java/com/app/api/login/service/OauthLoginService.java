package com.app.api.login.service;


import com.app.api.login.dto.OauthLoginDto;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.external.oauth.model.OAuthAttributes;
import com.app.external.oauth.service.SocialLoginApiService;
import com.app.external.oauth.service.SocialLoginApiServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {
        //어떤 소셜 로그인 API를 할지, member Type으로 구현체를 받는다.
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);

        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}", userInfo);

        Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
        if (optionalMember.isEmpty()) {// 신규 회원
            Member oauthMember = userInfo.toMemberEntity(memberType, Role.ADMIN);
            memberService.registerMember(oauthMember);
        } else { // 기존 회원일 경우

        }

        return OauthLoginDto.Response.builder().build();
    }
}
