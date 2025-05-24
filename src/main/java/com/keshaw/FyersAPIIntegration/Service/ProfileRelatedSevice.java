package com.keshaw.FyersAPIIntegration.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keshaw.FyersAPIIntegration.CommonUtil.CommonUtils;
import com.keshaw.FyersAPIIntegration.DTO.FundResponse;
import com.keshaw.FyersAPIIntegration.DTO.HoldingResponse;
import com.keshaw.FyersAPIIntegration.DTO.UserProfileResponse;
import com.keshaw.FyersAPIIntegration.Exceptions.InvalidRequestException;
import com.keshaw.FyersAPIIntegration.Logger.Log;
import com.tts.in.model.FyersClass;
import com.tts.in.utilities.Tuple;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileRelatedSevice {

    @Autowired
    FyersClass fyersClass;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    ObjectMapper objectMapper;

    public UserProfileResponse getProfileData(String clientId, String fyersToken) throws InvalidRequestException, JsonProcessingException {
        fyersClass.clientId = clientId;
        fyersClass.accessToken = fyersToken;
        Tuple<JSONObject, JSONObject> getProfileData = fyersClass.GetProfile();
        commonUtils.checkForExceptionFromTuple(getProfileData);

        JSONObject jsonObject = getProfileData.Item1();
        Log.consoleLog("Profile data >> "  + jsonObject);
        UserProfileResponse response = objectMapper.readValue(jsonObject.toString(), UserProfileResponse.class);
        return response;
    }

    public List<FundResponse> getFundDetails(String clientId, String fyersToken) throws InvalidRequestException, JsonProcessingException {
        fyersClass.clientId = clientId;
        fyersClass.accessToken = fyersToken;
        Tuple<JSONObject, JSONObject> getFunds = fyersClass.GetFunds();
        commonUtils.checkForExceptionFromTuple(getFunds);

        JSONObject jsonObject = getFunds.Item1();
        Log.consoleLog("Fund data >> "  + jsonObject);
        JSONArray jsonArray = getFunds.Item1().getJSONObject("data").getJSONArray("fund_limit");
        List<FundResponse> response = objectMapper.readValue(jsonArray.toString(), new TypeReference<List<FundResponse>>() {});
        return response;
    }

    public HoldingResponse getHoldings(String clientId, String fyersToken) throws Exception {
        fyersClass.clientId = clientId;
        fyersClass.accessToken = fyersToken;
        Tuple<JSONObject, JSONObject> getHoldings = fyersClass.GetHoldings();
        commonUtils.checkForExceptionFromTuple(getHoldings);

        JSONObject jsonObject = getHoldings.Item1();
        Log.consoleLog("Hondlings data >> "  + jsonObject);

        HoldingResponse.Overall overall = objectMapper.readValue(jsonObject.getJSONObject("overall").toString(), HoldingResponse.Overall.class);
        ArrayList<HoldingResponse.Holding> holding = objectMapper.readValue(jsonObject.getJSONArray("holdings").toString(), new TypeReference<ArrayList<HoldingResponse.Holding>>(){});

        HoldingResponse response = new HoldingResponse();
        response.setOverall(overall);
        response.setHoldings(holding);

        return response;
    }
}
