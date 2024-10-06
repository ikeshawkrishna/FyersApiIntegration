package com.keshaw.FyersAPIIntegration.GoNoGo;

import com.keshaw.FyersAPIIntegration.AOP.LoggerAspect;
import com.keshaw.FyersAPIIntegration.Model.APIModel.*;
import com.keshaw.FyersAPIIntegration.Repository.BankNiftyRepository;
import com.keshaw.FyersAPIIntegration.Repository.NiftyRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

@RestController
public class OptionValidation {
    private static final String STATUS = "status";
    private static final String MSG = "msg";
    private static final String Exception = "Exception in Fyers API Level";
    public static final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private final String jsonFilePath = "K:/Keshaw/fyers API/Springboot/output.json";


    @Autowired
    NiftyRepository niftyRepository;
    @Autowired
    BankNiftyRepository bankNiftyRepository;
    @Autowired
    FrameCustomInput frameCustomInput;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);


    @GetMapping("/GetData")
    @ResponseBody
    @Scheduled(fixedRate = 60000)
    public Maindata getData() {
        JSONObject FProfileData = frameCustomInput.FilterProfileData();
        String resp = getJsonData();
        JSONObject FOptionDataNifty = frameCustomInput.FilterOptionData("NSE:NIFTY50-INDEX");

        //For Testing
//        JSONObject FOptionData = new JSONObject(resp);
//        log.info("Inside FOptionData >> " + FOptionData);

        // Create profile maindata
        ProfileData profileData = new ProfileData();
        profileData.setFy_id(FProfileData.getString("fy_id"));
        profileData.setName(FProfileData.getString("name"));
        profileData.setEmail_id(FProfileData.getString("email_id"));
        profileData.setMobile_number(FProfileData.getString("mobile_number"));

        // Create Nifty maindata
        NiftyData niftyData = new NiftyData();
        niftyData.setTotalCallOI(FOptionDataNifty.getString("call_Oi"));
        niftyData.setCallOIChange(FOptionDataNifty.getString("call_Oichange"));
        niftyData.setCallVolume(FOptionDataNifty.getString("call_volumechange"));

        niftyData.setTotalPutOI(FOptionDataNifty.getString("put_Oi"));
        niftyData.setPutOIChange(FOptionDataNifty.getString("put_Oichange"));
        niftyData.setPutVolume(FOptionDataNifty.getString("put_volumechange"));

        niftyData.setDatetime(FOptionDataNifty.getString("date"));
        niftyData.setInstrumentName("NSE:NIFTY50-INDEX");
        niftyRepository.save(niftyData);


        // Create Bank Nifty maindata (can be similar)
        JSONObject FOptionDataBN = frameCustomInput.FilterOptionData("NSE:NIFTYBANK-INDEX");
        BankNiftyData bankNiftyData = new BankNiftyData();
        bankNiftyData.setTotalCallOI(FOptionDataBN.getString("call_Oi"));
        bankNiftyData.setCallOIChange(FOptionDataBN.getString("call_Oichange"));
        bankNiftyData.setCallVolume(FOptionDataBN.getString("call_volumechange"));

        bankNiftyData.setTotalPutOI(FOptionDataBN.getString("put_Oi"));
        bankNiftyData.setPutOIChange(FOptionDataBN.getString("put_Oichange"));
        bankNiftyData.setPutVolume(FOptionDataBN.getString("put_volumechange"));

        bankNiftyData.setDatetime(FOptionDataBN.getString("date"));
        bankNiftyData.setInstrumentName("NSE:NIFTYBANK-INDEX");
        bankNiftyRepository.save(bankNiftyData);

        // Create OptionData
        OptionData optionData = new OptionData();
        optionData.setNifty(niftyData);
        optionData.setBankNifty(bankNiftyData);

        // Create Maindata object
        Maindata maindata = new Maindata();
        maindata.setProfileData(profileData);
        maindata.setOptiondata(optionData);

        LOGGER.info("final output >> " + maindata);
        return maindata;
    }


    //For testing JPA
    public String readJsonFile() throws Exception {
        Path path = Paths.get(jsonFilePath);
        return new String(Files.readAllBytes(path));
    }

    @GetMapping("/getJsonData")
    public String getJsonData() {
        try {
            String resp = readJsonFile();
            JSONObject obj = new JSONObject(resp);
            System.out.println("obj ::::  " + obj);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Unable to read JSON data\"}";
        }
    }

}
