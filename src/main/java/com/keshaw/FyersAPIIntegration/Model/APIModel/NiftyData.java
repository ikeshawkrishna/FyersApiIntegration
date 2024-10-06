package com.keshaw.FyersAPIIntegration.Model.APIModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
@Entity
public class NiftyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int SNo;
    private String instrumentName;
    private String datetime;
    private String totalCallOI;
    private String totalPutOI;
    private String CallOIChange;
    private String PutOIChange;
    private String CallVolume;
    private String PutVolume;

}
