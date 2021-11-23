package com.leantech.booking.controller;

import com.leantech.booking.config.MessagingConfig;
import com.leantech.booking.model.BadRequest;
import com.leantech.booking.model.Booking;
import com.leantech.booking.model.BookingRequest;
import com.leantech.booking.model.BookingStatus;
import com.leantech.booking.model.Conflict;
import com.leantech.booking.model.GetBookingIdResponse;
import com.leantech.booking.model.GetBookingIdResponseData;
import com.leantech.booking.model.GetBookingsResponse;
import com.leantech.booking.model.GetBookingsResponseData;
import com.leantech.booking.model.ResourceNotFound;
import com.leantech.booking.model.ResourceNotFoundError;
import com.leantech.booking.service.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Objects;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private Object response;

    private HttpStatus httpStatus;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BookingService bookingService;

    @ApiOperation(value = "Return all bookings")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "bookings matching criteria", response = GetBookingIdResponse.class),})
    @GetMapping("/consultar-reservas")
    public ResponseEntity<GetBookingsResponse> getAllBookings() {
        List<Booking> bookings = bookingService.findAllBookings();

        return new ResponseEntity<>(new GetBookingsResponse(200, new GetBookingsResponseData(bookings)), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Booking")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "bookings matching criteria", response = GetBookingIdResponse.class),
        @ApiResponse(code = 404, message = "booking not found for bookingId", response = ResourceNotFound.class)
    })
    @GetMapping("/consultar-reserva/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") Long id) {

        Booking booking = bookingService.findBookingById(id);

        if (Objects.nonNull(booking)) {
            response = new GetBookingIdResponse(200, new GetBookingIdResponseData(booking));
            httpStatus = HttpStatus.OK;
        } else {
            response = new ResourceNotFound(404, new ResourceNotFoundError(404, "active booking not found"));
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @ApiOperation(value = "Add Booking")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "booking created", response = GetBookingIdResponse.class),
        @ApiResponse(code = 400, message = "There were validation errors in your request. See error details for more information.", response = BadRequest.class),
        @ApiResponse(code = 409, message = "an existing item already exists", response = Conflict.class)
    })
    @PostMapping("/registrar-reserva")
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest booking) {
        BadRequest badRequ = bookingService.validateData(booking);
        Booking addBooking = null;

        if (Objects.isNull(badRequ)) {
            addBooking = bookingService.addBooking(booking);
            response = new GetBookingIdResponse(200, new GetBookingIdResponseData(addBooking));
            httpStatus = HttpStatus.CREATED;
            System.out.println("status = " + httpStatus.name());
        } else {
            response = badRequ;
            httpStatus = HttpStatus.BAD_REQUEST;
            System.out.println("status = " + httpStatus.name());
        }

        //RabbitMq
        if (httpStatus.name().equals("CREATED")) {
        BookingStatus bookingStatus = new BookingStatus(addBooking, null, httpStatus.name(), addBooking.getTitularReserva(), "booking succesfully to " + addBooking.getTitularReserva());
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, bookingStatus);
        } else {
        BookingStatus bookingStatus = new BookingStatus(null, badRequ, httpStatus.name(), addBooking.getTitularReserva(), "booking error to " + addBooking.getTitularReserva());
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, bookingStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @ApiOperation(value = "Updates an active booking")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "booking created", response = GetBookingIdResponse.class)
    })
    @PutMapping("/actualizar-reserva")
    public ResponseEntity<?> updateBooking(@RequestBody Booking booking) {
        Booking updateBooking = bookingService.updateBooking(booking);

        return new ResponseEntity<>(new GetBookingIdResponse(200, new GetBookingIdResponseData(updateBooking)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an active booking")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "booking deleted", response = GetBookingIdResponse.class)
    })
    @DeleteMapping("/eliminar-reserva/{id}")
    public ResponseEntity<?> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
