package com.leantech.booking.consumer;

import com.leantech.booking.config.MessagingConfig;
import com.leantech.booking.model.BadRequest;
import com.leantech.booking.model.Booking;
import com.leantech.booking.model.BookingStatus;
import com.leantech.booking.model.EmailTemplate;
import com.leantech.booking.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Edrd
 */
@Component
public class Consumer {

    @Autowired
    private BookingService bookingService;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(BookingStatus bookingStatus) {
        System.out.println("Message recieved from queue : " + bookingStatus);
        EmailTemplate email = new EmailTemplate();
        if (bookingStatus.getStatus().equals("CREATED")) {
            email.setSendTo(bookingStatus.getBooking().getTitularReserva().concat("@gmail.com"));
            email.setSubject("Confirmación de Reserva");
            email.setBody(makeBodySuccess(bookingStatus.getBooking()));
        } 
        else {
            email.setSendTo(bookingStatus.getBooking().getTitularReserva().concat("@gmail.com"));
            email.setSubject("Confirmación de Reserva");
            email.setBody(makeBodyError(bookingStatus.getBadRequest(), bookingStatus.getTitularReserva()));
        }
        System.out.println(bookingService.sendTextEmail(email));
    }

    private String makeBodySuccess(Booking bkng) {
        return "Hola," + bkng.getTitularReserva() + "\n"
                + "\n"
                + "Gracias por elegir Hotel Paraiso. ¡Estamos deseando alojarte!\n"
                + "\n"
                + "Este mensaje es una confirmación de tu reserva para las fechas " + bkng.getFechaIngreso() + " al " + bkng.getFechaSalida() + "\n"
                + "\n"
                + "Aquí te dejamos todos los detalles de tu reserva:\n"
                + "Número de confirmación de reserva: " + bkng.getId() + "\n"
                + "Fecha de llegada: " + bkng.getFechaIngreso() + "\n"
                + "Fecha de salida: " + bkng.getFechaSalida() + "\n"
                + "Total de personas: " + bkng.getNumeroPersonas();
    }
    
    private String makeBodyError(BadRequest bdRq, String titularReserva) {
        
        return "Hola," + titularReserva + "\n"
                + "\n"
                + "Gracias por elegir Hotel Paraiso.\n"
                + "\n"
                + "Lo sentimos pero no ha sido posible llevar a cabo tu reserva por los siguientes motivos:\n"
                + bdRq.getError().getDetails().get(0).getMessage();
                
    }

}
