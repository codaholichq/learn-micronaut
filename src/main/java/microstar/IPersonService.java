package microstar;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public interface IPersonService {
    List<PersonDto> findAll();
    PersonDto findById(UUID id);
    PersonDto create(PersonRequestDto requestDto);
    PersonDto update(UUID id, PersonRequestDto requestDto);
    void delete(UUID id);
}
