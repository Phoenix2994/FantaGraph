package graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "model","controller"} )
public class APIExposer {
    public static void main(String[] args) {
        SpringApplication.run(APIExposer.class, args);
    }
}