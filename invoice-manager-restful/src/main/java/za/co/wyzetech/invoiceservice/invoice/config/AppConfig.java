package za.co.wyzetech.invoiceservice.invoice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"za.co.wyzetech.invoiceservice.invoice"})
@EnableAutoConfiguration
public class AppConfig {
    private static final Logger Log = LoggerFactory.getLogger(AppConfig.class);
}
