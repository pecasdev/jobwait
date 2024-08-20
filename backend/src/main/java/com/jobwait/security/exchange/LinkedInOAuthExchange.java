package com.jobwait.security.exchange;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.jobwait.security.AuthToken;
import com.jobwait.security.SecurityFaults;
import com.jobwait.security.UUIDJumbler;
import com.jobwait.security.linkedin.LinkedInUserProfile;
import com.jobwait.util.Tuple;

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

        return localClient
                .post()
                .uri(linkedInAccessTokenURI)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(AuthToken.class)
                .onErrorMap(err -> SecurityFaults.FailToGetTokenFault(err.getMessage()))
                .block().authToken;
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
                .bodyToMono(LinkedInUserProfile.class)
                .onErrorMap(err -> SecurityFaults.FailToGetUserIDFault(err.getMessage()))
                .block().sub;

        return UUID.fromString(linkedInUUID);
    }

    @Override
    public Tuple<String, UUID> getUUIDAndHash(String authCode) {
        UUID userID = this.getUserUUID(authCode);
        String hashString = UUIDJumbler.jumbleUUID(userID);
        return new Tuple<String, UUID>(hashString, userID);
    }
}
