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
public class OptionService {
    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String Exception = "Exception in Fyers API Level";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    public JSONObject getOptionData(String symbol) {
        JSONObject resp = new JSONObject();

        FyersClass fyersClass = FyersClass.getInstance();
        fyersClass.clientId = Constant.clientID;
        fyersClass.accessToken = Constant.Token;
        OptionService app = new OptionService();

        //String symbol = "NSE:NIFTY50-INDEX";
        int strikeCount = 3;
        String timestamp = "";

        JSONObject optionData = app.GetOptionChain(fyersClass, symbol, strikeCount, timestamp);
        if (optionData != null && !optionData.isEmpty()) {
            resp.put(STATUS, "true");
            resp.put(MSG, optionData);
        }
        LOGGER.info("Inside getOptionData resp >> " + resp);
        return resp;
    }

    public JSONObject GetOptionChain(FyersClass fyersClass, String symbol, int strikeCount, String timestamp) {
        JSONObject optionData = null;
        Tuple<JSONObject, JSONObject> optionTuple = fyersClass.GetOptionChain(symbol, strikeCount, timestamp);
        if (optionTuple.Item2() == null) {
            optionData = optionTuple.Item1();
        } else {
            optionData = optionTuple.Item2();
        }
        return optionData;
    }
}
