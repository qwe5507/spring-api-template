package com.app.external.oauth.kakao.service;

import com.app.domain.member.constant.MemberType;
import com.app.external.oauth.kakao.client.KaKaoUserInfoClient;
import com.app.external.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.app.external.oauth.model.OAuthAttributes;
import com.app.external.oauth.service.SocialLoginApiService;
import com.app.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KaKaoUserInfoClient kaKaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        KakaoUserInfoResponseDto kakaoUserInfoResponseDto = kaKaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken);// accessToken 앞에 bearer 붙여야 함.
        KakaoUserInfoResponseDto.KakaoAccount kaKaoAccount = kakaoUserInfoResponseDto.getKaKaoAccount();
        String email = kaKaoAccount.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
                .name(kaKaoAccount.getProfile().getNickname())
                .profile(kaKaoAccount.getProfile().getThumbnailImageUrl())
                .memberType(MemberType.KAKAO)
                .build();
    }
}
