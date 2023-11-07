package com.goldenBet.GoldenBet.controller;

import com.goldenBet.GoldenBet.dto.EventoDTO;
import com.goldenBet.GoldenBet.models.Evento;
import com.goldenBet.GoldenBet.service.IServiceEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/evento")
public class ControllerEvento {

    private final IServiceEvento serviceEvento;

    @Autowired
    public ControllerEvento(
            @Qualifier("ServiceEvento") IServiceEvento eventoService
    ) {
        serviceEvento = eventoService;
    }

    @PostMapping("/save")
    public void save(@RequestBody EventoDTO eventoDTO) {
        serviceEvento.create(eventoDTO);
    }

    @GetMapping("/all")
    public List<Evento> getAll() {
        return serviceEvento.getAll();
    }

    //GetMapping endpoint para getByParticipante conjunto
    @GetMapping("/getbyparticipante/{participanteName}")
    public Set<Evento> getByParticipante(@PathVariable String participanteName) {
        return serviceEvento.getByParticipante(participanteName);
    }

    //GetMapping endpoint para getByCompetenciaName
    @GetMapping("/getbycompetencia/{competenciaName}")
    public List<Evento> getByCompetencia(@PathVariable String competenciaName) {
        return serviceEvento.getByCompetenciaName(competenciaName);
    }
}
