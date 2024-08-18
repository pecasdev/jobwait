package com.jobwait.security.exchange;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import com.jobwait.security.AuthToken;
import com.jobwait.security.linkedin.LinkedInUserProfile;

public final class LinkedInOAuthExchange extends OAuthExchange {
    private static WebClient localClient = WebClient.create();

    private static String codeToBearerToken(String authCode) {
        // Everything except authCode should be pulled in from ENV
        final String clientId = "";
        final String clientSecret = "";
        final String redirectURI = "";
        final String linkedInAccessTokenURI = "https://www.linkedin.com/oauth/v2/accessToken";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", authCode);
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("redirect_uri", redirectURI);

        // handle error [Specific error (bad response)]
        // AND
        // [Generic error because unlikely (timeout) or (no body)]
        return localClient
                .post()
                .uri(linkedInAccessTokenURI)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(AuthToken.class).block().authToken;
    }

    @Override
    public UUID getUserUUID(String authCode) {
        String bearerToken = codeToBearerToken(authCode);

        String linkedInUserInfoURI = "https://api.linkedin.com/v2/userinfo";

        String linkedInUUID = localClient
                .get()
                .uri(linkedInUserInfoURI)
                .headers(h -> h.setBearerAuth(bearerToken))
                .retrieve()
                .bodyToMono(LinkedInUserProfile.class).block().sub;

        return UUID.fromString(linkedInUUID);
    }
}
