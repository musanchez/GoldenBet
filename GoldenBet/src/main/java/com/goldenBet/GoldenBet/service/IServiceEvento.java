package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.EventoDTO;
import com.goldenBet.GoldenBet.models.Evento;

import java.util.List;

public interface IServiceEvento {
    void create(EventoDTO eventoDTO);

    List<Evento> getAll();
}
