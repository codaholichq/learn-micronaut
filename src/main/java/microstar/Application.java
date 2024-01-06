package microstar;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "Microstar",
                version = "1.0",
                description = "Microstar API",
                contact = @Contact(
                        url = "https://codaholic.com",
                        name = "Codaholic",
                        email = "hello@codaholic.com"
                )
        )
)

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}