package com.leantech.booking;

import com.leantech.booking.model.Booking;
import com.leantech.booking.model.BookingRequest;
import com.leantech.booking.repository.BookingRepository;
import com.leantech.booking.service.BookingService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingApplicationTests {

    @Autowired
    private BookingService service;

    @MockBean
    private BookingRepository repository;

    @Test
    public void addBookingTest() throws ParseException {
        BookingRequest bkng = new BookingRequest();
        bkng.setFechaIngreso("2021-11-12");
        bkng.setFechaSalida("2021-11-13");
        bkng.setTitularReserva("Pedro Paramo");
        bkng.setEmailTitular("pedro@mail.com");
        bkng.setNumeroHabitaciones(1);
        bkng.setNumeroPersonas(2);
        bkng.setNumeroMenores(1);

        Booking bk = new Booking();
        bk.setId(null);
        bk.setFechaIngreso(Date.from(LocalDate.of(2021,11,12).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bk.setFechaSalida(Date.from(LocalDate.of(2021,11,13).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bk.setTitularReserva("Pedro Paramo");
        bk.setEmailTitular("pedro@mail.com");
        bk.setNumeroHabitaciones(1);
        bk.setNumeroPersonas(2);
        bk.setNumeroMenores(1);
        bk.setTotalDias(2L);
        
        when(repository.save(bk)).thenReturn(bk);
        assertEquals(bk, service.addBooking(bkng));
    }

    @Test
    public void findAllBookingsTest() {
        when(repository.findAll())
                .thenReturn(Stream.of(
                        new Booking(1L, "Sonia Morales", "sonia@email.com", 2, 1, 1, Date.from(LocalDate.of(2021, 1, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.of(2021, 1, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()), 5L),
                        new Booking(1L, "Hugo Sanchez", "hugo@email.com", 2, 1, 1, Date.from(LocalDate.of(2021, 1, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.of(2021, 1, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()), 5L)
                        )
                        .collect(Collectors.toList()));
        assertEquals(2, service.findAllBookings().size());
    }

    @Test
    public void findBookingByIdTest() {
        when(repository.findBookingById(anyLong()))
                .thenReturn(Optional.of(
                        new Booking(1L, "Julio Preciado", "julio@email.com", 6, 2, 3, Date.from(LocalDate.of(2021, 1, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.of(2021, 1, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()), 5L)
                        ));

        assertNotNull(service.findBookingById(1L));
    }

    @Test
    public void updateBookingTest() {
        Booking bk = new Booking();
        bk.setId(1L);
        bk.setFechaIngreso(new Date());
        bk.setFechaSalida(new Date());
        bk.setTitularReserva("Laura Romo");
        bk.setEmailTitular("laura@mail.com");
        bk.setNumeroHabitaciones(1);
        bk.setNumeroPersonas(2);
        bk.setNumeroMenores(1);
        bk.setTotalDias(2L);

        when(repository.save(bk)).thenReturn(bk);
        assertEquals(bk, service.updateBooking(bk));
    }

    @Test
    public void deleteBookingTest() {
        Booking bk = new Booking();
        bk.setId(1L);
        bk.setFechaIngreso(Date.from(LocalDate.of(2021, 10, 21).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bk.setFechaSalida(Date.from(LocalDate.of(2021, 10, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        bk.setTitularReserva("Carlos Trejo");
        bk.setEmailTitular("carlos@mail.com");
        bk.setNumeroHabitaciones(2);
        bk.setNumeroPersonas(10);
        bk.setNumeroMenores(3);
        bk.setTotalDias(2L);

        service.deleteBooking(1L);
        verify(repository, times(1)).delete(bk);
    }

}
