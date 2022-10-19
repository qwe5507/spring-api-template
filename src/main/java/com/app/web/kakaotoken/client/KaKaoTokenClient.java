package com.app.web.kakaotoken.client;

import com.app.web.kakaotoken.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")
public interface KaKaoTokenClient {

    @PostMapping(value = "/oauth/token", consumes = "application/json")
    KakaoTokenDto.Response requestKaKaoToken(@RequestHeader("Content-Type") String contentType,
                                             @SpringQueryMap KakaoTokenDto.Request request
    );

}
