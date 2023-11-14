package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.EventoDTO;
import com.goldenBet.GoldenBet.models.Evento;

import java.util.List;
import java.util.Set;

public interface IServiceEvento {
    void create(EventoDTO eventoDTO);

    List<Evento> getAll();

    //abstraccion de metodo para GET por nombre de patcicipante (conjunto, SET)
    Set<Evento> getByParticipantes(String participanteName);

    //abstraccion de funcion para GET por nombre de competencia (list)
    List<Evento> getByCompetencia(String competenciaName);

    //abstracción de función para GET por estado de evento
    List<Evento> getByEstado(String estado);

    //abstracción de función para GET por descripcion de evento
    List<Evento> getByDescripcion(String descripcion);

    //abstracción de función para GET por el deporte de la competencia del evento
    List<Evento> getByDeporte(String deporte);

}












//Set<Evento> getByParticipante(String participanteName);

//List<Evento> getByCompetenciaName(String competenciaName);