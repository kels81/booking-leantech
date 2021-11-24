package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotEmpty(message = "{titularReserva.empty}")
    private String titularReserva;
    @NotEmpty(message = "{emailTitular.empty}")
    private String emailTitular;
    @Digits(integer = 10, fraction = 0)
    private int numeroPersonas;
    @Digits(integer = 10, fraction = 0)
    private int numeroHabitaciones;
    @Digits(integer = 10, fraction = 0)
    private int numeroMenores;
    @NotEmpty(message = "{fechaIngreso.empty}")
    private Date fechaIngreso;
    @NotEmpty(message = "{fechaSalida.empty}")
    private Date fechaSalida;
    private Long totalDias;

}
