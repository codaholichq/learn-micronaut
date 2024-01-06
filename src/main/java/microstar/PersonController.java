package microstar;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import static io.micronaut.http.HttpResponse.ok;

@Validated
@Controller("/api/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    private IPersonService personService;

    @Get
    public HttpResponse<List<PersonDto>> findAll() {
        return ok(personService.findAll());
    }

    @Get("{id}")
    public HttpResponse<PersonDto> findById(@NotNull @PathVariable("id") UUID id) {
        return ok(personService.findById(id));
    }

    @Post
    public HttpResponse<PersonDto> create(@Body @Valid PersonRequestDto requestDto) {
        return ok(personService.create(requestDto));
    }

    @Put("{id}")
    public HttpResponse<PersonDto> update(
            @PathVariable("id") UUID id,
            @Body @Valid PersonRequestDto requestDto
    ) {
        return ok(personService.update(id, requestDto));
    }

    @Delete("{id}")
    public HttpResponse<?> delete(@PathVariable UUID id) {
        personService.delete(id);
        return HttpResponse.noContent();
    }
}
