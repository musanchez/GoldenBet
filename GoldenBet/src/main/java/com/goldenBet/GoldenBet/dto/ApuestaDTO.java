package com.goldenBet.GoldenBet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApuestaDTO {
    @JsonProperty("Descripcion_Apuesta")
    private final String descripcion;

    @JsonProperty("Monto")
    private final BigDecimal monto;

    //@JsonProperty("ID_Admin_Encargado")
    //private final String idAdmin;

    @JsonProperty("ID_Apostador")
    private final String idUsuario;

    @JsonProperty("ID_Evento")
    private final String idEvento;
}
