package com.goldenBet.GoldenBet.controller;

import com.goldenBet.GoldenBet.dto.CompetenciaDTO;
import com.goldenBet.GoldenBet.models.Competencia;
import com.goldenBet.GoldenBet.service.IServiceCompetencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competencia")
public class ControllerCompetencia {

    private final IServiceCompetencia serviceCompetencia;

    @Autowired
    public ControllerCompetencia(
            @Qualifier("ServiceCompetencia") IServiceCompetencia competenciaService
    ) {
        serviceCompetencia = competenciaService;
    }

    @PostMapping("/save")
    public void save(@RequestBody CompetenciaDTO competenciaDTO) {
        serviceCompetencia.create(competenciaDTO);
    }

    @GetMapping("/all")
    public List<Competencia> getAll() {
        return serviceCompetencia.getAll();
    }
}
