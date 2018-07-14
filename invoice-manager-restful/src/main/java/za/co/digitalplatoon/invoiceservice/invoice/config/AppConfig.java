package za.co.digitalplatoon.invoiceservice.invoice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"za.co.digitalplatoon.invoiceservice.invoice"})
@EnableAutoConfiguration
public class AppConfig {
    private static final Logger Log = LoggerFactory.getLogger(AppConfig.class);
}
