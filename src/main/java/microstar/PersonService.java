package microstar;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

public class PersonService implements IPersonService {

    @Inject
    private PersonMapper mapper;

    @Inject
    private PersonRepository personRepository;

//    public PersonService(PersonMapper mapper, PersonRepository personRepository) {
//        this.mapper = mapper;
//        this.personRepository = personRepository;
//    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findAll() {
        return personRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonDto findById(UUID id) {
        return personRepository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
    }

    @Override
    @Transactional
    public PersonDto create(PersonRequestDto requestDto) {
        var person = Person.builder()
                .firstName(requestDto.firstName())
                .lastName(requestDto.lastName())
                .email(requestDto.email())
                .age(requestDto.age())
                .build();

        personRepository.save(person);
        var personDto = mapper.toDto(person);
        return personDto;
    }

    @Override
    @Transactional
    public PersonDto update(UUID id, PersonRequestDto requestDto) {
        var person = personRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));

        person.setFirstName(requestDto.firstName());
        person.setLastName(requestDto.lastName());
        person.setEmail(requestDto.email());
        person.setAge(requestDto.age());

        var updatedPerson = personRepository.update(person);
        var personDto = mapper.toDto(updatedPerson);
        return personDto;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        personRepository
                .findById(id)
                .ifPresent(personRepository::delete);
    }
}
