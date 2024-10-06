package com.keshaw.FyersAPIIntegration.Model.APIModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class OptionData {

    @JsonProperty("nifty")
    private NiftyData Nifty;
    @JsonProperty("banknifty")
    private BankNiftyData BankNifty;
}
