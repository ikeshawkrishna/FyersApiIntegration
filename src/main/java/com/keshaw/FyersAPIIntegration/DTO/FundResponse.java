package com.keshaw.FyersAPIIntegration.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundResponse {
    private int commodityAmount;
    private double equityAmount;
    private int id;
    private String title;
}
