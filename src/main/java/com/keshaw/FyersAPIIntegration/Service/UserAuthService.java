package com.keshaw.FyersAPIIntegration.Service;

import com.keshaw.FyersAPIIntegration.Exceptions.InvalidRequestException;
import com.tts.in.model.FyersClass;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    FyersClass fyersClass;

    @Value("${fyers.redirect.uri}")
    String redirectURI;

    public String generateFyersToken(String clientId, String code, String secretKey) throws InvalidRequestException {
        fyersClass.clientId = clientId;
        
        String hashId = getSecretIdSHA256(clientId, secretKey);
        JSONObject jsonObject = fyersClass.GenerateToken(code, secretKey);

        //jsonObject sample output
        //jobj.put("RESPONSE_MESSAGE", message);
        //    jobj.put("access_token", accessToken);
        //    jobj.put("refresh_token", refreshToken);

        String access_token = jsonObject.getString("access_token");
        if(access_token.isEmpty()){
            throw new InvalidRequestException(jsonObject.getString("RESPONSE_MESSAGE"));
        }
        return access_token;
    }

    private static String getSecretIdSHA256(String appId, String secretId){
        return DigestUtils.sha256Hex(appId + ":" + secretId);
    }

    public void redirectUserLogin(String clientId) {
        fyersClass.clientId = clientId;
        fyersClass.GenerateCode(redirectURI);
    }

}
