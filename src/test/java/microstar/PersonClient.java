package microstar;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Client("/api/person")
public interface PersonClient {

    @Get
    List<PersonDto> findAll();

    @Get("{id}")
    PersonDto findById(@PathVariable UUID id);

    @Post
    HttpResponse<PersonDto> create(@Body PersonRequestDto requestDto);

    @Put("{id}")
    HttpResponse<PersonDto> update(@PathVariable UUID id, @Body PersonRequestDto requestDto);

    @Delete("{id}")
    HttpResponse<?> delete(@PathVariable UUID id);
}

