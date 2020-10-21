package pl.kskowronski;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.helpers.LaunchUtil;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracjeDTO;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the  * and some desktop browsers.
 *
 */
@SpringBootApplication
@PWA(name = "nap_emp_portal", shortName = "nap_emp_portal")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}
