package com.goldenBet.GoldenBet.service;


import com.goldenBet.GoldenBet.dto.DeporteDTO;
import com.goldenBet.GoldenBet.models.Deporte;
import java.util.List;

public interface IServiceDeporte {
    void create(DeporteDTO deporteDTO);

    List<Deporte> getAll();
}
