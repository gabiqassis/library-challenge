package dev.gabiqassis.hering.domain.mapper;

import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.domain.response.CountryResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryMapperTest {

    private final CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

    @Test
    public void testMap() {
        CountryDTO countryDTO = new CountryDTO("brasileiro", "Brasil", "Brazil", "BR");

        CountryResponse countryResponse = mapper.map(countryDTO);

        assertEquals(countryDTO.gentilico(), countryResponse.gentilico());
        assertEquals(countryDTO.nomePais(), countryResponse.nomePais());
        assertEquals(countryDTO.nomePaisInt(), countryResponse.nomePaisInt());
        assertEquals(countryDTO.sigla(), countryResponse.sigla());
    }
}


