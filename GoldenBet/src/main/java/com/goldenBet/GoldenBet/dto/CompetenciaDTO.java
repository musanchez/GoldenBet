package com.goldenBet.GoldenBet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompetenciaDTO {

    @JsonProperty("Nombre_Competencia")
    private String nombre;

    @JsonProperty("Deporte_ID")
    private String deporteId;
}
