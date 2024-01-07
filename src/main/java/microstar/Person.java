package microstar;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Serdeable
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    public String firstName;

    @Column(length = 50)
    public String lastName;

    @Column(length = 100, unique = true)
    public String email;

    public int age;
}
