package com.keshaw.FyersAPIIntegration.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HoldingResponse {

    private Overall overall;
    private ArrayList<Holding> holdings;

    public static class Overall{
        public double total_pl;
        public int count_total;
        public double total_investment;
        public double total_current_value;
        public double pnl_perc;
    }

    public static class Holding{
        public int remainingQuantity;
        public String symbol;
        public int quantity;
        public double costPrice;
        public int qty_t1;
        public double ltp;
        public String fyToken;
        public double marketVal;
        public int remainingPledgeQuantity;
        public int collateralQuantity;
        public String holdingType;
        public int segment;
        public int exchange;
        public int id;
        public double pl;
        public String isin;
    }

}


