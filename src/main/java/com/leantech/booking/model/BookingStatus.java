package com.leantech.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Edrd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingStatus {

    private Booking booking;
    private BadRequest badRequest;
    private String status;
    private String titularReserva;
    private String message;

}
