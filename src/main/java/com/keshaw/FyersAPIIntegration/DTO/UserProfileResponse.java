package com.keshaw.FyersAPIIntegration.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileResponse {

    private String name;
    private String display_name;
    private String email_id;
    private String PAN;
    private String fy_id;
    private String mobile_number;
    private boolean mtf_enabled;

}
