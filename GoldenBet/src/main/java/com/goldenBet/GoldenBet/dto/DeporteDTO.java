package com.goldenBet.GoldenBet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeporteDTO {
    @JsonProperty("Nombre_Deporte")
    private String nombre;
}
