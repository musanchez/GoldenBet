package com.goldenBet.GoldenBet.controller;

import com.goldenBet.GoldenBet.dto.DeporteDTO;
import com.goldenBet.GoldenBet.models.Deporte;
import com.goldenBet.GoldenBet.service.ServiceDeporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deporte")
public class ControllerDeporte {

    private final ServiceDeporte serviceDeporte;

    @Autowired
    public ControllerDeporte(
            @Qualifier("ServiceDeporte") ServiceDeporte deporteService
    ) {
        serviceDeporte = deporteService;
    }

    @PostMapping("/save")
    public void save(@RequestBody DeporteDTO deporteDTO) {
        serviceDeporte.create(deporteDTO);
    }

    @GetMapping("/all")
    public List<Deporte> getAll() {
        return serviceDeporte.getAll();
    }
}
