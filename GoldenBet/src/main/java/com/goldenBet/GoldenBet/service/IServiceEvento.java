package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.EventoDTO;
import com.goldenBet.GoldenBet.models.Evento;

import java.util.List;
import java.util.Set;

public interface IServiceEvento {
    void create(EventoDTO eventoDTO);

    List<Evento> getAll();

    Set<Evento> getByParticipante(String participanteName);

    List<Evento> getByCompetenciaName(String competenciaName);
}
