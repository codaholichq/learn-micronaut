package microstar;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@MicronautTest
public class PersonControllerTest {

    @Inject
    private PersonClient client;

    @Test
    public void testCreate() {
        var requestDto = new PersonRequestDto("John", "Smith", "jsmith@codaholic.com", 33);
        var response = client.create(requestDto);
        var person = response.body();

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatus());
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.id());
        Assertions.assertInstanceOf(UUID.class, person.id(), "ID should be a UUID");
    }
}
