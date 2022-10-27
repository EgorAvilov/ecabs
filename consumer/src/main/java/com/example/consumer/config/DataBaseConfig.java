package com.example.consumer.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;


@Configuration
public class DataBaseConfig {

    private static final String CONNECTION_NAME = "jdbc:postgresql://localhost:5432/ecabs";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    @Bean
    DataSource dataSource() {
        String uri = System.getenv("DATABASE_URL");
       // String uri = "postgres://toejowpvrmnxef:6bf8a7ecefc5e0619d2524f585d987a40d58c41291e64f0a17db4262c335e81f@ec2-54-228-218-84.eu-west-1.compute.amazonaws.com:5432/ddoqj9ph4d6fpp";
        URI url = null;
        try {
            uri=uri.replace("postgres", "postgresql");
            url = new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        uri=uri.replace("postgres", "postgresql");
        String ar=uri.substring(uri.indexOf("//")+2,uri.indexOf("@")+1);
        HikariDataSource hikariDataSource=new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:"+url.toString().replace(ar,""));
        hikariDataSource.setUsername(url.getUserInfo().split(":")[0]);
        hikariDataSource.setPassword(url.getUserInfo().split(":")[1]);

        return hikariDataSource;
    }
}
