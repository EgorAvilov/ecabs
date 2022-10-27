package com.example.consumer.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RabbitConfig {
    private static final String CONNECTION_NAME = "rabbitmq";

    @Bean
    public ConnectionFactory connectionFactory()
    {

        String uri = System.getenv("CLOUDAMQP_URL");
        //String uri = "amqps://otkktcxr:09_S2G3MA99uJ60H_B6hu2OODLj1JMY-@stingray.rmq.cloudamqp.com/otkktcxr";
        if (uri == null) //значит мы запущены локально и нужно подключаться к локальному rabbitmq
            return new CachingConnectionFactory(CONNECTION_NAME);
        URI url = null;
        try
        {
            url = new URI(uri);
        } catch (URISyntaxException e)
        {
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
}