package org.baeldung.config;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

//generateToken自定义 access token的claims,每次token签发都会调用.
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        Map<String,Object> ret = new HashMap<>();
        additionalInfo.put("tenantId", authentication.getName() + randomAlphabetic(4));
        additionalInfo.put("deviceId", "11-22-33");
        additionalInfo.put("clienId", authentication.getOAuth2Request().getClientId());
        additionalInfo.put("mobile", "13776639873");
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;       
        
    }
}
