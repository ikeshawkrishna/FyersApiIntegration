package com.keshaw.FyersAPIIntegration.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.keshaw.FyersAPIIntegration.DTO.FundResponse;
import com.keshaw.FyersAPIIntegration.DTO.UserProfileResponse;
import com.keshaw.FyersAPIIntegration.Exceptions.InvalidRequestException;
import com.keshaw.FyersAPIIntegration.Service.ProfileRelatedSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ProfileRelatedController {

    @Autowired
    ProfileRelatedSevice profileRelatedSevice;

    @GetMapping("/getProfile")
    public UserProfileResponse getProfileData(@RequestHeader("clientId") String clientId,  @RequestHeader("fyersToken") String fyersToken) throws InvalidRequestException, JsonProcessingException {
        return profileRelatedSevice.getProfileData(clientId, fyersToken);
    }

    @GetMapping("/getFundDetails")
    public List<FundResponse> getFundDetails(@RequestHeader("clientId") String clientId, @RequestHeader("fyersToken") String fyersToken) throws InvalidRequestException, JsonProcessingException {
        return profileRelatedSevice.getFundDetails(clientId, fyersToken);
    }

    @GetMapping("/getHoldings")
    public Object getHoldings(@RequestHeader("clientId") String clientId, @RequestHeader("fyersToken") String fyersToken) throws Exception {
        return profileRelatedSevice.getHoldings(clientId, fyersToken);
    }
}
