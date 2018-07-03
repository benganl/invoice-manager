package za.co.digitalplatoon.invoiceservice.invoice.config;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"za.co.digitalplatoon.invoiceservice.invoice"})
@EnableAutoConfiguration
// @PropertySource("classpath:config/config.properties")
public class AppConfig {
    private static final Logger Log = LoggerFactory.getLogger(AppConfig.class);

    // @Value("${databaseFile}")
    // private String databaseFile;

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
	ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
	registrationBean.addUrlMappings("/h2/*");
	return registrationBean;

    }
}
