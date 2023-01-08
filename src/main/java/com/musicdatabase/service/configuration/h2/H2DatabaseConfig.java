package com.musicdatabase.service.configuration.h2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Profile("JDBC")
@Component
public class H2DatabaseConfig implements CommandLineRunner {
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:file:./db/vinyldb")
                .username("")
                .password("")
                .build();
        return dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:./db/vinyldb", "", "")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM album")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("title"));
                    }
                }
            }
        }
    }
}
