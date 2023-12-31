package com.goldenBet.GoldenBet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventoDTO {
    /*
    ahorita la fecha y hora se asignan automaticamente con .now(), después lo cambiamos cuando
    el sitio web tenga el ingreso gráfico, y el estado ahorita se pone "Programado" por defecto al crear

    @JsonProperty("Fecha_Evento")
    private final LocalDate fecha;

    @JsonProperty("Nombre_Competencia")
    private final LocalTime hora;

    @JsonProperty("Estado_Evento")
    private final String estado;

     */

    @JsonProperty("Descripcion_Evento")
    private final String descripcion;

    @JsonProperty("Primer_Participante")
    private final String participante1;

    @JsonProperty("Segundo_Participante")
    private final String participante2;

    @JsonProperty("Competencia_ID")
    private final String competenciaId;
}
