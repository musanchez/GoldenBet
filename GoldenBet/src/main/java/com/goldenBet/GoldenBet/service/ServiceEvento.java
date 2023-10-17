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
import java.util.List;

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

        //atributos del POJO a asignar mediante DTO
        Evento evento = new Evento();
        String idEvento = "evt-" + (repoEvento.count() + 1);
        String primerParticipante = eventoDTO.getParticipante1();
        String segundoParticipante = eventoDTO.getParticipante2();
        LocalDate fechaEvento = LocalDate.now();
        LocalTime horaEvento = LocalTime.now();
        Competencia competenciaAsignada = repoCompetencia.findById(idCompetenciaAsignada).get();

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
}
