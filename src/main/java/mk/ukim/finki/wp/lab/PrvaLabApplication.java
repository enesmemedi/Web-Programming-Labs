package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PrvaLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrvaLabApplication.class, args);
    }

}
