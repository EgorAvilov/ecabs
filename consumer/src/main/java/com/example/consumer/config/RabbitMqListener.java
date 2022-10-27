package com.example.consumer.config;

import com.example.consumer.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.consumer.utils.Constants.*;

@EnableRabbit
@Component
@Log4j2
public class RabbitMqListener {

    private final BookingService bookingService;
    @Autowired
    public RabbitMqListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = BOOKING_CREATE_QUEUE_NAME)
    public String processQueueCreateBooking(String message) throws IOException {
       return bookingService.create(message);
    }
    @RabbitListener(queues = BOOKING_DELETE_QUEUE_NAME)
    public void processQueueDeleteBooking(String message) throws IOException {
        bookingService.delete(message);
    }
    @RabbitListener(queues = BOOKING_UPDATE_QUEUE_NAME)
    public String processQueueUpdateBooking(String message) throws IOException {
       return bookingService.update(message);
    }

    @RabbitListener(queues = BOOKING_UPDATE_QUEUE_NAME)
    public String processQueueGetAllBookings() throws IOException {
        return bookingService.getAll();
    }

    @RabbitListener(queues = BOOKING_UPDATE_QUEUE_NAME)
    public String processQueueGetByIdBooking(String message) throws IOException {
        return bookingService.getById(message);
    }

    @RabbitListener(queues = MESSAGE_AUDIT_QUEUE_NAME)
    public void processQueueMessageAudit(String message) {
        System.out.println("Received from queue " + MESSAGE_AUDIT_QUEUE_NAME + " : " + message);
    }
}
