package com.game.oauth.test.proxy.example;

import org.springframework.util.Assert;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:31 2019/6/10 0010
 * @explain :
 */
public class ExampleOne {

    private  String clientId;

    private  String clientSecret;

    private  String accessTokenUrl;

    /**
     * Constructs an OAuth2Template for a given set of client credentials.
     * Assumes that the authorization URL is the same as the authentication URL.
     * @param clientId the client ID
     * @param clientSecret the client secret
     * @param authorizeUrl the base URL to redirect to when doing authorization code or implicit grant authorization
     * @param accessTokenUrl the URL at which an authorization code, refresh token, or user credentials may be exchanged for an access token.
     */
    public ExampleOne(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        this(clientId, clientSecret, authorizeUrl, null, accessTokenUrl);
    }

    public ExampleOne(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        Assert.notNull(clientId, "The clientId property cannot be null");
        Assert.notNull(clientSecret, "The clientSecret property cannot be null");
        Assert.notNull(authorizeUrl, "The authorizeUrl property cannot be null");
        Assert.notNull(accessTokenUrl, "The accessTokenUrl property cannot be null");
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        if (authenticateUrl != null) {
        } else {
        }
        this.accessTokenUrl = accessTokenUrl;
    }
}
