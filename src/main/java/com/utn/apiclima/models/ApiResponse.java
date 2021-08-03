package com.utn.apiclima.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder

public class ApiResponse {

    @JsonProperty("hora")
    private String hora;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("alerts")
    private List<String> alerts;

    @JsonProperty("city_name")
    private String city_name;

    public void setHora() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora =  dtf.format(LocalDateTime.now());;
    }
}
