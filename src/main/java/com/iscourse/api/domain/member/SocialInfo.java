package com.iscourse.api.domain.member;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class SocialInfo {
    private String provider;
    private String providerId;
    private String accessToken;
    private String refreshToken;
}
