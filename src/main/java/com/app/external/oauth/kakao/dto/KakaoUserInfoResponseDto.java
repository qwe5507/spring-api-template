package com.app.external.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfoResponseDto {

    private String id;

    @JsonProperty("kakao-account")
    private KakaoAccount kaKaoAccount;

    @Getter @Setter
    public static class KakaoAccount {
        private String email;
        private Profile profile;
    }

    @Getter @Setter
    public static class Profile {
        private String nickname;
        @JsonProperty("thumbnail_image_url")
        private String thumbnailImageUrl;
    }
}
