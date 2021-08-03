package com.utn.apiclima.controllers;

import com.utn.apiclima.models.ApiResponse;
import com.utn.apiclima.services.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @Operation(description = "EndPoint que devuelve la hora actual, configurado en Mar del Plata")
    @GetMapping("/climaNow")
    public ApiResponse clima_now() throws IOException, InterruptedException {
        return apiService.getClimaNow();
    }
}
