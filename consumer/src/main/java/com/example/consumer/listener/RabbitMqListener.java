package com.example.consumer.listener;

import com.example.consumer.service.BookingService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(RabbitMqListener.class);
    private final BookingService bookingService;
    @Autowired
    public RabbitMqListener(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = BOOKING_CREATE_QUEUE_NAME)
    public String processQueueCreateBooking(String message) throws IOException {
       return bookingService.createBooking(message);
    }
    @RabbitListener(queues = BOOKING_DELETE_QUEUE_NAME)
    public void processQueueDeleteBooking(String message) throws IOException {
        bookingService.deleteBooking(message);
    }
    @RabbitListener(queues = BOOKING_UPDATE_QUEUE_NAME)
    public String processQueueUpdateBooking(String message) throws IOException {
       return bookingService.updateBooking(message);
    }

    @RabbitListener(queues = MESSAGE_AUDIT_QUEUE_NAME)
    public void processQueueMESSAGE_AUDIT_QUEUE_NAME(String message) {
        logger.info("Received from queue " + MESSAGE_AUDIT_QUEUE_NAME + " : " + message);
    }
}
