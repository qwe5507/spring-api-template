package com.app.api.login.dto;

import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

public class OauthLoginDto {

    @Getter @Setter
    public static class Request {
        @Schema(description = "소셜 로그인 회원 타입", example = "KAKAO", required = true)
        private String memberType; // kakao, naver, etc...
    }

    @Getter @Setter
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {

        @Schema(description = "grantType", example = "Bearer", required = true)
        private String grantType;

        @Schema(description = "accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE2Njc5MTczNjQsImV4cCI6MTY2NzkxODI2NCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.OC8ub7YllNb_MxhiqujHKOj4i_0eP7jT228O_qw3vU41M2HBQUVkAwtnyXzZOwVUUuKMzLheWkAxJGyNHgUK0A", required = true)
        private String accessToken;

        @Schema(description = "access token 만료 시간", example = "2022-09-11 06:38:11", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;

        @Schema(description = "refreshToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSRUZSRVNIIiwiaWF0IjoxNjY4MTE1MzkxLCJleHAiOjE2NjkzMjQ5OTEsIm1lbWJlcklkIjoxfQ.SjyirGMbgTa4jDF33VcqdmI4qMm0bZ0xqPYT08h0vxO5XPyiHghCQlN5psxFiSFBtOWicvIUCUSbJOzLFBOQaQ", required = true)
        private String refreshToken;

        @Schema(description = "refresh token 만료 시간", example = "2022-09-11 06:38:11", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        public static Response of (JwtTokenDto jwtTokenDto) {
            return Response.builder()
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .build();
        }
    }
}
