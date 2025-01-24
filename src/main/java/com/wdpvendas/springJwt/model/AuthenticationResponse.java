package com.wdpvendas.springJwt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    // @JsonProperty("username")
    // private String username;

    // @JsonProperty("role")
    // private Role role;

    // @JsonProperty("user_id")
    // private Integer id;

    public AuthenticationResponse(String accessToken, String refreshToken, String message) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }

    // public AuthenticationResponse(String accessToken, String refreshToken, String username, String message) {
    //     this.accessToken = accessToken;
    //     this.refreshToken = refreshToken;
    //     this.username = username;
    //     this.message = message;
    // }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }

}
