package microstar;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.UUID;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

    @Inject
    private PersonClient client;

    private static UUID createdPersonId;

    @Test
    @Order(1)
    public void testCreate() {
        var requestDto = new PersonRequestDto("John", "Smith", "jsmith@codaholic.com", 33);
        var response = client.create(requestDto);
        var person = response.body();

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatus());
        Assertions.assertNotNull(person);
        Assertions.assertNotNull(person.id());
        Assertions.assertInstanceOf(UUID.class, person.id(), "ID should be a UUID");

        createdPersonId = person.id();
    }

    @Test
    @Order(2)
    public void testFindById() {
        var foundPerson = client.findById(createdPersonId);
        Assertions.assertNotNull(foundPerson);
    }

    @Test
    @Order(3)
    public void testFindAll() {
        var persons = client.findAll();
        Assertions.assertFalse(persons.isEmpty());
    }

    @Test
    @Order(4)
    public void testUpdate() {
        var requestDto = new PersonRequestDto("John", "Smith", "jsmith@codaholic.com", 34);
        var response = client.update(createdPersonId, requestDto);
        var person = response.body();

        Assertions.assertEquals(HttpStatus.OK, response.getStatus(), "Expected 200 OK status");
        Assertions.assertNotNull(person);
        Assertions.assertEquals(createdPersonId, person.id(), "ID should remain the same");

//        Assertions.assertEquals("UpdatedJohn", person.firstName(), "First name should be updated");
//        Assertions.assertEquals("UpdatedSmith", person.lastName(), "Last name should be updated");
//        Assertions.assertEquals("updatedjsmith@codaholic.com", person.email(), "Email should be updated");
        Assertions.assertEquals(34, person.age(), "Age should be updated");
    }

    @Test
    @Order(5)
    public void testDelete() {
        var person = client.delete(createdPersonId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, person.getStatus());
    }
}
