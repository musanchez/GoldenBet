package com.goldenBet.GoldenBet.service;

import com.goldenBet.GoldenBet.dto.ApuestaDTO;
import com.goldenBet.GoldenBet.models.Apuesta;
import java.util.List;

public interface IServiceApuesta {
    void create(ApuestaDTO apuestaDTO);

    List<Apuesta> getAll();
}
