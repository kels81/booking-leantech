package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
    @JsonProperty(value = "emailTitular", required = true)
    @NotEmpty(message = "{emailTitular.empty}")
    private String emailTitular;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    @NotEmpty(message = "{fechaIngreso.empty}")
    private String fechaIngreso;
    @JsonProperty(value = "fechaSalida", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    @NotEmpty(message = "{fechaSalida.empty}")
    private String fechaSalida;
    
}
