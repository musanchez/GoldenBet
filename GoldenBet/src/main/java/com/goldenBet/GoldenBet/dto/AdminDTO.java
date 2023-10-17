package com.goldenBet.GoldenBet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdminDTO {
    @JsonProperty("Nombre_Admin")
    private final String nombre;

    @JsonProperty("Cedula_Admin")
    private final String cedula;
}
