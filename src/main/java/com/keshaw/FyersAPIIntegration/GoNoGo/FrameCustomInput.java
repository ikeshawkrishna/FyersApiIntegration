package com.keshaw.FyersAPIIntegration.GoNoGo;

import com.keshaw.FyersAPIIntegration.Service.OptionService;
import com.keshaw.FyersAPIIntegration.Service.ProfileService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Component
public class FrameCustomInput {
    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String Exception = "Exception in Fyers API Level";
    public static final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    Logger log = com.keshaw.FyersAPIIntegration.Logger.customLogger.log;

    @Autowired
    OptionService optionService;
    @Autowired
    ProfileService profileService;

    public JSONObject FilterProfileData() {
        JSONObject resp = new JSONObject();
        try {
            String fy_id = "", name, email_id, mobile_number;

            JSONObject ProfileResponse = profileService.getProfileDetails();
            String status = ProfileResponse.get("status").toString();
            if (!status.equalsIgnoreCase("true")) {
                resp.put(STATUS, "false");
                resp.put(MSG, Exception);
                return resp;
            }

            ProfileResponse = ProfileResponse.getJSONObject("msg");
            fy_id = ProfileResponse.getString("fy_id");
            name = ProfileResponse.getString("name");
            email_id = ProfileResponse.getString("email_id");
            mobile_number = ProfileResponse.getString("mobile_number");

            resp.put("fy_id", fy_id);
            resp.put("name", name);
            resp.put("email_id", email_id);
            resp.put("mobile_number", mobile_number);
            log.info("FilterProfileData final resp >> " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


    public JSONObject FilterOptionData(String symbol) {
        JSONObject resp = new JSONObject();
        try {
            long put_Oi = 0;
            long call_Oi = 0;
            long put_Oichange = 0;
            long put_volumechange = 0;
            long call_Oichange = 0;
            long call_volumechange = 0;

            JSONObject OptionResponse = optionService.getOptionData(symbol);
            log.info("OptionResponse >> " + OptionResponse);
            String status = OptionResponse.get("status").toString();
            if (!status.equalsIgnoreCase("true")) {
                resp.put(STATUS, "false");
                resp.put(MSG, Exception);
                return resp;
            }

            OptionResponse = OptionResponse.getJSONObject("msg");
            call_Oi = OptionResponse.getJSONObject("data").getLong("callOi");
            put_Oi = OptionResponse.getJSONObject("data").getLong("putOi");
            JSONArray joptionsChain = OptionResponse.getJSONObject("data").getJSONArray("optionsChain");

            for (int i = 1; i < joptionsChain.length(); i = i + 2) {
                JSONObject joptionsChainData = joptionsChain.getJSONObject(i);

                //total put oi change
                long oich = joptionsChainData.getLong("oich");
                put_Oichange += oich;

                //total put volume change
                long volume = joptionsChainData.getLong("volume");
                put_volumechange += volume;
            }

            for (int i = 2; i < joptionsChain.length(); i = i + 2) {
                JSONObject joptionsChainData = joptionsChain.getJSONObject(i);

                //total put oi change
                long oich = joptionsChainData.getLong("oich");
                call_Oichange += oich;

                //total put volume change
                long volume = joptionsChainData.getLong("volume");
                call_volumechange += volume;
            }

            resp.put("call_Oi", "Rs." + decimalFormat.format(call_Oi));
            resp.put("call_Oichange", "Rs." + decimalFormat.format(call_Oichange));
            resp.put("call_volumechange", "Rs." + decimalFormat.format(call_volumechange));

            resp.put("put_Oi", "Rs." + decimalFormat.format(put_Oi));
            resp.put("put_Oichange", "Rs." + decimalFormat.format(put_Oichange));
            resp.put("put_volumechange", "Rs." + decimalFormat.format(put_volumechange));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = LocalDateTime.now().format(formatter);

            resp.put("date", date);

            log.info("FilterOptionData final resp >> " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
}
