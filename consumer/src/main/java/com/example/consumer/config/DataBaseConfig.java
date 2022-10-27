package com.example.consumer.config;

import com.zaxxer.hikari.HikariDataSource;
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
        URI url = null;
        try {
            uri=uri.replace("postgres", "postgresql");
            url = new URI(uri);
        } catch (URISyntaxException e) {
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
