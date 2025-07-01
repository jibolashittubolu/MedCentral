package com.example.medcentral.feature.hospital.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class HospitalUpdateRequest {
    @JsonIgnore
    private String hospitalId;

    private String hospitalName;
    private String hospitalAddress;
    private String hospitalCity;
    private String hospitalState;
    private String hospitalPhone;
    private String hospitalEmail;
}

