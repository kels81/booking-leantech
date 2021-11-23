package com.leantech.booking.model;

import java.io.Serializable;
import java.time.LocalDate;
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
    @Digits(integer =10,fraction = 0)
    private int numeroPersonas;
    @Digits(integer =10,fraction = 0)
    private int numeroHabitaciones;
    @Digits(integer =10,fraction = 0)
    private int numeroMenores;
    @NotEmpty(message = "{fechaIngreso.empty}")
    private LocalDate fechaIngreso;
    @NotEmpty(message = "{fechaSalida.empty}")
    private LocalDate fechaSalida;
    private Long totalDias;
    
}
