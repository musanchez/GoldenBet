package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.CompetenciaDTO;
import com.goldenBet.GoldenBet.models.Competencia;
import java.util.List;

public interface IServiceCompetencia {
    void create(CompetenciaDTO competenciaDto);

    List<Competencia> getAll();
}
