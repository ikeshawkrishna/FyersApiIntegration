package com.keshaw.FyersAPIIntegration.Model.APIModel;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProfileData {
    private String fy_id;
    private String name;
    private String email_id;
    private String mobile_number;
}
