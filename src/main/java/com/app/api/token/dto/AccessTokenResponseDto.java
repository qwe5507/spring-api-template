package com.app.api.token.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Builder
public class AccessTokenResponseDto {

    @Schema(description = "grantType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE2Njc5MTczNjQsImV4cCI6MTY2NzkxODI2NCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.OC8ub7YllNb_MxhiqujHKOj4i_0eP7jT228O_qw3vU41M2HBQUVkAwtnyXzZOwVUUuKMzLheWkAxJGyNHgUK0A", required = true)
    private String accessToken;

    @Schema(description = "access token 만료 시간", example = "2022-09-11 06:38:11", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd:HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;
}
