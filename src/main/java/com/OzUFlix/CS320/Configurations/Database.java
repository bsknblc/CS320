package com.OzUFlix.CS320.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Database
{
    @Bean
    public DataSource MySQLDataSource()
    {
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/CS320_project?serverTimezone=UTC");
        source.setUsername("root");
        source.setPassword("12345r");

        return source;
    }
}
