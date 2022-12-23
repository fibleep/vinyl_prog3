package com.musicdatabase.service.configuration.psql;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Profile("psql")
@Component
public class PSQLDatabaseConfig {
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/vinyldb")
                .username("postgres")
                .password("student1234")
                .build();
        return dataSource;
    }
}
