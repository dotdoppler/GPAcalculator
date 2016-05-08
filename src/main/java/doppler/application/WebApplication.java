package doppler.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by doppler on 2016/5/8.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("doppler")
public class WebApplication extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter{
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
