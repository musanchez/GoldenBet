package com.goldenBet.GoldenBet.controller;

import com.goldenBet.GoldenBet.dto.ApuestaDTO;
import com.goldenBet.GoldenBet.models.Apuesta;
import com.goldenBet.GoldenBet.service.IServiceApuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apuesta")
public class ControllerApuesta {

    private final IServiceApuesta serviceApuesta;

    @Autowired
    public ControllerApuesta(
            @Qualifier("ServiceApuesta") IServiceApuesta apuestaService
    ) {
        serviceApuesta = apuestaService;
    }

    @PostMapping("/save")
    public void save(@RequestBody ApuestaDTO apuestaDTO) {
        serviceApuesta.create(apuestaDTO);
    }

    @GetMapping("/getall")
    public List<Apuesta> getAll() {
        return serviceApuesta.getAll();
    }
}
