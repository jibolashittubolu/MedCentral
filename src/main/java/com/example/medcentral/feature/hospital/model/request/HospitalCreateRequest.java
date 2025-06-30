package com.example.medcentral.feature.hospital.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class HospitalCreateRequest {

    @NotBlank(message = "specify the hospitalName")
    private String hospitalName;

    @NotBlank(message = "specify the hospitalAddress")
    private String hospitalAddress;

    @NotBlank(message = "specify the hospitalCity")
    private String hospitalCity;

    @NotBlank(message = "specify the hospitalState")
    private String hospitalState;

    @NotBlank(message = "specify the hospitalPhone")
    private String hospitalPhone;

    @NotBlank(message = "specify the hospitalEmail")
    private String hospitalEmail;

}
