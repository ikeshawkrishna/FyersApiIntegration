package com.keshaw.FyersAPIIntegration.Controller;

import com.keshaw.FyersAPIIntegration.Exceptions.InvalidRequestException;
import com.keshaw.FyersAPIIntegration.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAuth")
public class UserAuthController {

    @Autowired
    UserAuthService userAuthService;

    @GetMapping("/userLogin")
    public void userlogin(@RequestParam("clientId") String clientId) {
        userAuthService.redirectUserLogin(clientId);
    }

    @GetMapping("/getFyersToken")
    public String generateFyersToken(@RequestParam("clientId") String clientId, @RequestParam("code") String code, @RequestParam("secretKey") String secretKey) throws InvalidRequestException {
        return userAuthService.generateFyersToken(clientId, code, secretKey);
    }

}
