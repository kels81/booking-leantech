package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookingRequest
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @JsonProperty(value = "titularReserva", required = true)
    @NotEmpty(message = "{titularReserva.empty}")
    private String titularReserva;
    @JsonProperty(value = "numeroPersonas", required = true)
    @Digits(integer =10,fraction = 0)
    private int numeroPersonas;
    @JsonProperty(value = "numeroHabitaciones", required = true)
    @Digits(integer =10,fraction = 0)
    private int numeroHabitaciones;
    @JsonProperty(value = "numeroMenores", required = true)
    @Digits(integer =10,fraction = 0)
    private int numeroMenores;
    @JsonProperty(value = "fechaIngreso", required = true)
    @NotEmpty(message = "{fechaIngreso.empty}")
    private String fechaIngreso;
    @JsonProperty(value = "fechaSalida", required = true)
    @NotEmpty(message = "{fechaSalida.empty}")
    private String fechaSalida;
    
}
