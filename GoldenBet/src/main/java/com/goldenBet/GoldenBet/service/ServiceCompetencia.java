package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.CompetenciaDTO;
import com.goldenBet.GoldenBet.models.Competencia;
import com.goldenBet.GoldenBet.models.Deporte;
import com.goldenBet.GoldenBet.repository.IRepositoryCompetencia;
import com.goldenBet.GoldenBet.repository.IRepositoryDeporte;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ServiceCompetencia")
public class ServiceCompetencia implements IServiceCompetencia{

    private final IRepositoryCompetencia repoCompetencia;
    private final IRepositoryDeporte repoDeporte;

    @Autowired
    public ServiceCompetencia(
            @Qualifier("RepoCompetencia") IRepositoryCompetencia competenciaRepo,
            @Qualifier("RepoDeporte") IRepositoryDeporte deporteRepo
    ) {
        repoCompetencia = competenciaRepo;
        repoDeporte = deporteRepo;
    }

    @Override
    @SneakyThrows
    public void create(CompetenciaDTO competenciaDto) {

        String idDeporteAsignado = competenciaDto.getDeporteId();
        boolean deporte_existe = repoDeporte.existsById(idDeporteAsignado);

        if(!deporte_existe)
            throw new Exception("ERROR, el deporte asignado a la competencia no existe");

        //atributos del modelo POJO a asignar mediante DTO
        Competencia competencia = new Competencia();
        String idCompetencia = "cmp-" + (repoCompetencia.count() + 1);
        String nombreCompetencia = competenciaDto.getNombre();
        Deporte deporteAsignado = repoDeporte.findById(idDeporteAsignado).get();

        competencia.setId(idCompetencia);
        competencia.setNombre(nombreCompetencia);
        competencia.setDeporte(deporteAsignado);

        repoCompetencia.save(competencia);
    }

    @Override
    public List<Competencia> getAll() {
        return repoCompetencia.findAll();
    }
}
