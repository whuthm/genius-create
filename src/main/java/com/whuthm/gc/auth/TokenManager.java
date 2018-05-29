package com.whuthm.gc.auth;

public interface TokenManager {

    String createToken(String userId);

    void deleteToken(String token);

    boolean checkToken(String token);


}
