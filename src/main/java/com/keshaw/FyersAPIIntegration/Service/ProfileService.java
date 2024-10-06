package com.keshaw.FyersAPIIntegration.Service;

import com.keshaw.FyersAPIIntegration.AOP.LoggerAspect;
import com.keshaw.FyersAPIIntegration.Constants.Constant;
import com.tts.in.model.FyersClass;
import com.tts.in.utilities.Tuple;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String Exception = "Exception in Fyers API Level";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    public JSONObject getProfileDetails() {
        JSONObject resp = new JSONObject();

        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = Constant.clientID;
        fyersClass.accessToken = Constant.Token;
        ProfileService app = new ProfileService();

        JSONObject profileData = app.GetProfile(fyersClass);
        if (profileData != null && !profileData.isEmpty()) {
            resp.put(STATUS, "true");
            resp.put(MSG, profileData);
        }
        LOGGER.info("Inside getProfileDetails resp >> " + resp);
        return resp;
    }

    public JSONObject GetProfile(FyersClass fyersClass) {
        Tuple<JSONObject, JSONObject> ProfileResponseTuple = fyersClass.GetProfile();
        JSONObject profile = null;
        if (ProfileResponseTuple.Item2() == null) {
            profile = ProfileResponseTuple.Item1();
        } else {
            profile = ProfileResponseTuple.Item2();
        }
        return profile;
    }
}
