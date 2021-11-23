package com.leantech.booking.service;

import com.leantech.booking.exception.BookingNotFoundException;
import com.leantech.booking.model.BadRequest;
import com.leantech.booking.model.BadRequestError;
import com.leantech.booking.model.Booking;
import com.leantech.booking.model.BookingRequest;
import com.leantech.booking.model.EmailTemplate;
import com.leantech.booking.model.Error;
import com.leantech.booking.repository.BookingRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BookingRepository bookingRepository;

    public Booking addBooking(BookingRequest bReq) {
        Booking booking = new Booking();
        booking.setTitularReserva(bReq.getTitularReserva());
        booking.setNumeroHabitaciones(bReq.getNumeroHabitaciones());
        booking.setNumeroPersonas(bReq.getNumeroPersonas());
        booking.setNumeroMenores(bReq.getNumeroMenores());
        booking.setFechaIngreso(convertStrDate1(bReq.getFechaIngreso()));
        booking.setFechaSalida(convertStrDate1(bReq.getFechaSalida()));
        booking.setTotalDias(getTotalDias(bReq));
        return bookingRepository.save(booking);
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findBookingById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking by id " + id + " was not found"));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteBookingById(id);
    }

    private LocalDate convertStrDate1(String strFecha) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        return LocalDate.parse(strFecha, inputFormatter);
    }

    private Long getTotalDias(BookingRequest bReq) {
        LocalDate dateStart = LocalDate.parse(bReq.getFechaIngreso());
        LocalDate dateEnd = LocalDate.parse(bReq.getFechaSalida());

        return ChronoUnit.DAYS.between(dateStart, dateEnd) + 1;
    }

    public BadRequest validateData(BookingRequest booking) {
        String msg = "hubo errores de validación en su solicitud";

        if (intDataValid(booking.getNumeroHabitaciones())) {
            return new BadRequest(400, new BadRequestError(400, msg, List.of(new Error(400, "numeroHabitaciones", "numero de habitaciones no puede estar vacio o igual a 0"))));
        }
        if (intDataValid(booking.getNumeroPersonas())) {
            return new BadRequest(400, new BadRequestError(400, msg, List.of(new Error(400, "numeroPersonas", "numero de personas no puede estar vacio o igual a 0"))));
        }
        if (strDataValid(booking.getTitularReserva())) {
            return new BadRequest(400, new BadRequestError(400, msg, List.of(new Error(400, "titularReserva", "el titular de la reserva no puede estar vacio"))));
        }
        if (!strDataDateValid(booking.getFechaIngreso())) {
            return new BadRequest(400, new BadRequestError(400, msg, List.of(new Error(400, "fechaIngreso", "la fecha de ingreso no puede estar vacio"))));
        }
        if (!strDataDateValid(booking.getFechaSalida())) {
            return new BadRequest(400, new BadRequestError(400, msg, List.of(new Error(400, "fechaSalida", "la fecha de salida no puede estar vacio"))));
        }

        return null;
    }

    private boolean intDataValid(int intValue) {
        return intValue <= 0;
    }

    private boolean strDataValid(String strValue) {
        return strValue.isBlank() || strValue.isEmpty();
    }

    private boolean strDataDateValid(String strValue) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObject = dateFormatter.parse(strValue);

        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    public String sendTextEmail(EmailTemplate email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getSendTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        try {
            javaMailSender.send(message);
            return "Email sent!";
        } catch (MailException e) {
            return "Eror in sending email " + e;
        }
    }

}
