package dev.gabiqassis.hering.domain.mapper;


import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.domain.response.CountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryResponse map(CountryDTO value);
}
