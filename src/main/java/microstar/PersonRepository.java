package microstar;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> { }
