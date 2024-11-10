package lk.tickets.Tickets.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lk.tickets.Tickets.models.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author danushka
 * 2024-11-10
 */
@Service
@Slf4j
public class ConfigService {
    private SystemConfig systemConfig;

    @PostConstruct
    public void init() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        File configFile = new File("config.json");
        if (configFile.exists()) {
            try {
                systemConfig = new ObjectMapper().readValue(configFile, SystemConfig.class);
                log.info("Configuration loaded successfully");
            } catch (IOException e) {
                log.error("Failed to load configuration", e);
                createDefaultConfiguration();
            }
        } else {
            createDefaultConfiguration();
        }
    }

    private void createDefaultConfiguration() {
        systemConfig = SystemConfig.builder()
                .total(1000)
                .releaseRate(1000)
                .consumerRate(2000)
                .maxCapacity(100)
                .build();
        saveConfiguration();
        log.info("Created default configuration");
    }

    private void saveConfiguration() {
        try {
            new ObjectMapper().writeValue(new File("config.json"), systemConfig);
            log.info("Configuration saved successfully");
        } catch (IOException e) {
            log.error("Failed to save configuration", e);
            throw new RuntimeException("Failed to save configuration", e);
        }
    }

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }
}
