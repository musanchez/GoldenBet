package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.EventoDTO;
import com.goldenBet.GoldenBet.models.Competencia;
import com.goldenBet.GoldenBet.models.Evento;
import com.goldenBet.GoldenBet.repository.IRepositoryCompetencia;
import com.goldenBet.GoldenBet.repository.IRepositoryEvento;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service("ServiceEvento")
public class ServiceEvento implements IServiceEvento{

    private final IRepositoryEvento repoEvento;
    private final IRepositoryCompetencia repoCompetencia;

    @Autowired
    public ServiceEvento(
            @Qualifier("RepoEvento") IRepositoryEvento eventoRepo,
            @Qualifier("RepoCompetencia") IRepositoryCompetencia competenciaRepo
    ) {
        repoEvento = eventoRepo;
        repoCompetencia = competenciaRepo;
    }

    @Override
    @SneakyThrows
    public void create(EventoDTO eventoDTO) {

        String idCompetenciaAsignada = eventoDTO.getCompetenciaId();
        boolean competencia_existe = repoCompetencia.existsById(idCompetenciaAsignada);

        if(!competencia_existe)
            throw new Exception("ERROR, la competencia asignada al evento no existe");

        boolean mismos_participantes = eventoDTO.getParticipante1()
                                            .equals(eventoDTO.getParticipante2());

        if(mismos_participantes)
            throw new Exception("ERROR, se ingresó el mismo participante dos veces");

        //atributos del POJO a asignar mediante DTO
        Evento evento = new Evento();
        String idEvento = "evt-" + (repoEvento.count() + 1);
        String primerParticipante = eventoDTO.getParticipante1().trim();
        String segundoParticipante = eventoDTO.getParticipante2().trim();
        LocalDate fechaEvento = LocalDate.now();
        LocalTime horaEvento = LocalTime.now();

        Competencia competenciaAsignada;

        if(repoCompetencia.findById(idCompetenciaAsignada).isPresent()) {
            competenciaAsignada = repoCompetencia.findById(idCompetenciaAsignada).get();
        } else {
            throw new Exception("ERROR al guardar el evento, conflicto en competencia");
        }

        //attachment
        evento.setId(idEvento);
        evento.setParticipante1(primerParticipante);
        evento.setParticipante2(segundoParticipante);
        evento.setFecha(fechaEvento);
        evento.setHora(horaEvento);
        evento.setCompetencia(competenciaAsignada);

        repoEvento.save(evento);
    }

    @Override
    public List<Evento> getAll() {
        return repoEvento.findAll();
    }

    //metodo que retorne SET de eventos filtrados por participante (ambos 1 y 2)
    @Override
    public Set<Evento> getByParticipante(String participanteName) {

        //lista 1 con primer Get
        //lista 2 con segundo Get

        //retornar Set de combinación de ambas listas

        List<Evento> lista1 = repoEvento.getByParticipanteUno(participanteName);
        List<Evento> lista2 = repoEvento.getByParticipanteDos(participanteName);

        Set<Evento> listaCombinada = new HashSet<>();
        listaCombinada.addAll(lista1);
        listaCombinada.addAll(lista2);

        return listaCombinada;
    }

    //metodo que retorne lista de eventos filtrados por competencia (nombre)
    @Override
    public List<Evento> getByCompetenciaName(String competenciaName) {
        return repoEvento.getByCompetenciaName(competenciaName);
    }
}
