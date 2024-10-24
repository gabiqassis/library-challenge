package dev.gabiqassis.hering.domain.mapper;


import dev.gabiqassis.hering.domain.entity.Author;
import dev.gabiqassis.hering.domain.request.AuthorCreateRequest;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "literaryWorks", ignore = true)
    Author map(AuthorCreateRequest value);

    AuthorResponse map(Author value);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "literaryWorks", ignore = true)
    void updateFromRequest(AuthorUpdateRequest request, @MappingTarget Author author);
}
