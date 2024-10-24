package dev.gabiqassis.hering.domain.mapper;


import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LiteraryWorkMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(ignore = true, target = "authors")
    LiteraryWork map(LiteraryWorkCreateRequest value);

    LiteraryWorkResponse map(LiteraryWork value);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicationDate", ignore = true)
    @Mapping(target = "authors", ignore = true)
    void map(LiteraryWorkUpdateRequest request, @MappingTarget LiteraryWork literaryWork);
}
