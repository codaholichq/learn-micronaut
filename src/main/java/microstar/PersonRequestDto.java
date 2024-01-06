package microstar;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Serdeable
public record PersonRequestDto(
        @NotEmpty(message = "First Name can not be empty")
        String firstName,

        @NotEmpty(message = "Last Name can not be empty")
        String lastName,

        @Email String email,

        @Min(18) int age
) implements Serializable {}

