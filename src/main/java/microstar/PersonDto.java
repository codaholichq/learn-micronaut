package microstar;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record PersonDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        int age
) {}

