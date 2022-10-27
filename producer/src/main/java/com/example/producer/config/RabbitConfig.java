package com.example.producer.config;

import com.example.producer.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class RabbitConfig {
    private static final String CONNECTION_NAME = "rabbitmq";

    @Bean
    public ConnectionFactory connectionFactory() {
        //получаем адрес AMQP у провайдера
        String uri = System.getenv("CLOUDAMQP_URL");
        //String uri = "amqps://otkktcxr:09_S2G3MA99uJ60H_B6hu2OODLj1JMY-@stingray.rmq.cloudamqp.com/otkktcxr";
        if (uri == null) //значит мы запущены локально и нужно подключаться к локальному rabbitmq
            return new CachingConnectionFactory(CONNECTION_NAME);
        URI url = null;
        try {
            url = new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(url.getHost());
        connectionFactory.setUsername(url.getUserInfo().split(":")[0]);
        connectionFactory.setPassword(url.getUserInfo().split(":")[1]);
        if (StringUtils.isNotBlank(url.getPath())) {
            connectionFactory.setVirtualHost(url.getPath().replace("/", ""));
        }
        connectionFactory.setConnectionTimeout(3000);
        connectionFactory.setRequestedHeartBeat(30);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue bookingCreateQueue() {
        return new Queue(Constants.BOOKING_CREATE_QUEUE_NAME);
    }

    @Bean
    public Queue bookingUpdateQueue() {
        return new Queue(Constants.BOOKING_UPDATE_QUEUE_NAME);
    }

    @Bean
    public Queue bookingDeleteQueue() {
        return new Queue(Constants.BOOKING_DELETE_QUEUE_NAME);
    }

    @Bean
    public Queue bookingGetAllQueue() {
        return new Queue(Constants.BOOKING_GET_ALL_QUEUE_NAME);
    }

    @Bean
    public Queue bookingGetByIdQueue() {
        return new Queue(Constants.BOOKING_GET_BY_ID_QUEUE_NAME);
    }

    @Bean
    public Queue messageAuditQueue() {
        return new Queue(Constants.MESSAGE_AUDIT_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange messageExchange() {
        return new FanoutExchange(Constants.MESSAGE_EXCHANGE);
    }

    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange(Constants.BOOKING_EXCHANGE);
    }

    @Bean
    public Binding bookingExchangeToMessageExchange() {
        return bind(bookingExchange()).to(messageExchange());
    }

    @Bean
    public Binding bookingCreateQueueToBookingExchange() {
        return bind(bookingCreateQueue()).to(bookingExchange())
                .with(Constants.CREATE_BOOKING_KEY);
    }

    @Bean
    public Binding bookingUpdateQueueToBookingExchange() {
        return bind(bookingUpdateQueue()).to(bookingExchange())
                .with(Constants.UPDATE_BOOKING_KEY);
    }

    @Bean
    public Binding bookingDeleteQueueToBookingExchange() {
        return bind(bookingDeleteQueue()).to(bookingExchange())
                .with(Constants.DELETE_BOOKING_KEY);
    }

    @Bean
    public Binding bookingGetAllQueueToBookingExchange() {
        return bind(bookingGetAllQueue()).to(bookingExchange())
                .with(Constants.GET_ALL_BOOKINGS_KEY);
    }

    @Bean
    public Binding bookingGetByIdQueueToBookingExchange() {
        return bind(bookingGetByIdQueue()).to(bookingExchange())
                .with(Constants.GET_BY_ID_BOOKING_KEY);
    }

    @Bean
    public Binding messageAuditQueueToMessageExchange() {
        return bind(messageAuditQueue()).to(messageExchange());
    }
}
