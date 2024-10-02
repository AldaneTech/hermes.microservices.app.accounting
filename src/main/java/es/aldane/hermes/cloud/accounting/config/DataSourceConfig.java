package es.aldane.hermes.cloud.accounting.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Autowired
    private HikariDataSource dataSource;

    @Bean
    public void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (dataSource != null && !dataSource.isClosed()) {
                dataSource.close(); // Cierra el pool de conexiones al apagar
            }
        }));
    }
}

