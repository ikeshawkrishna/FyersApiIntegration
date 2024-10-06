package com.keshaw.FyersAPIIntegration.Model.APIModel;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class Maindata {
    private ProfileData profileData;
    private OptionData Optiondata;
}
