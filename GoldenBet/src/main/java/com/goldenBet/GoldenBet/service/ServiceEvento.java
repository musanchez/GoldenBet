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

        //posibles excepciones
        boolean competencia_existe = repoCompetencia.existsById(idCompetenciaAsignada);

        boolean mismos_participantes = eventoDTO.getParticipante1()
                .equals(eventoDTO.getParticipante2());

        boolean descripcion_nula = eventoDTO.getDescripcion() == null || eventoDTO.getDescripcion().isBlank();

        if(!competencia_existe)
            throw new Exception("ERROR, la competencia asignada al evento no existe");


        if(mismos_participantes)
            throw new Exception("ERROR, se ingresó el mismo participante dos veces");

        if(descripcion_nula)
            throw new Exception("ERROR, no se ingresó una descripción válida");

        //atributos del POJO a asignar mediante DTO
        Evento evento = new Evento();
        String idEvento = "evt-" + (repoEvento.count() + 1);
        String primerParticipante = eventoDTO.getParticipante1().trim();
        String segundoParticipante = eventoDTO.getParticipante2().trim();
        String descripcion = eventoDTO.getDescripcion().trim();
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
        evento.setDescripcion(descripcion);
        evento.setEstado("PROGRAMADO");

        System.out.println("\n***EVENTO CREADO DE MANERA SATISFACTORIA, ALMACENADO EN LA BASE DE DATOS***\n");
        repoEvento.save(evento);
    }

    @Override
    public List<Evento> getByDescripcion(String descripcion) {
        List<Evento> eventosList = repoEvento.getByDescripcion(descripcion);

        for(Evento evt : eventosList) {
            System.out.println(evt.getId());
            System.out.println(evt.getDescripcion());
            System.out.println(evt.getEstado());
            System.out.println(evt.getParticipante1());
            System.out.println(evt.getParticipante1());
            System.out.println(evt.getCompetencia().getNombre());
            System.out.println(evt.getFecha());
            System.out.println(evt.getHora());
            System.out.println();
        }

        return eventosList;
    }

    @Override
    public List<Evento> getByEstado(String estado) {
        List<Evento> eventosList = repoEvento.getByEstado(estado);
        return eventosList;
    }

    @Override
    public List<Evento> getAll() {
        List<Evento> eventosList = repoEvento.findAll();

        for(Evento evt : eventosList) {
            System.out.println(evt.getId());
            System.out.println(evt.getDescripcion());
            System.out.println(evt.getEstado());
            System.out.println(evt.getParticipante1());
            System.out.println(evt.getParticipante1());
            System.out.println(evt.getCompetencia().getNombre());
            System.out.println(evt.getFecha());
            System.out.println(evt.getHora());
            System.out.println();
        }

        return eventosList;
    }

    @Override
    public Set<Evento> getByParticipantes(String participanteName) {
        List<Evento> lista1 = repoEvento.getByParticipante1(participanteName);
        List<Evento> lista2 = repoEvento.getByParticipante2(participanteName);

        Set<Evento> setCombinado = new HashSet<>();
        setCombinado.addAll(lista1);
        setCombinado.addAll(lista2);

        return setCombinado;
    }

    @Override
    public List<Evento> getByCompetencia(String competenciaName) {
        return repoEvento.getByCompetencia(competenciaName);
    }
}
