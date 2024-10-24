package dev.gabiqassis.hering.domain.mapper;

import dev.gabiqassis.hering.domain.entity.Author;
import dev.gabiqassis.hering.domain.request.AuthorCreateRequest;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorMapperTest {

    private AuthorMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(AuthorMapper.class);
    }

    @Test
    public void testMapCreateRequestToEntity() {
        AuthorCreateRequest request = new AuthorCreateRequest("Ana Souza", GenderEnum.FEMININO, "ana@example.com",
                LocalDate.of(1980, 1, 1), "Brazil", "12345678901");

        Author entity = mapper.map(request);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertEquals("Ana Souza", entity.getName());
        assertEquals(GenderEnum.FEMININO, entity.getGender());
        assertEquals("ana@example.com", entity.getEmail());
        assertEquals("12345678901", entity.getCpf());
        assertEquals("Brazil", entity.getCountryOrigin());
        assertNull(entity.getLiteraryWorks());
    }

    @Test
    public void testMapEntityToResponse() {
        Author entity = new Author();
        entity.setId(1L);
        entity.setName("Ana Souza");
        entity.setGender(GenderEnum.FEMININO);
        entity.setEmail("ana@example.com");
        entity.setCpf("12345678901");
        entity.setCountryOrigin("Brazil");
        entity.setBirthDate("01-01-2020");

        AuthorResponse response = mapper.map(entity);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Ana Souza", response.name());
        assertEquals(GenderEnum.FEMININO, response.gender());
        assertEquals("ana@example.com", response.email());
        assertEquals("12345678901", response.cpf());
        assertEquals("Brazil", response.countryOrigin());
        assertEquals("01-01-2020", response.birthDate());
    }

    @Test
    public void testUpdateFromRequestToEntity() {
        AuthorUpdateRequest request = new AuthorUpdateRequest("Ana Dois", "ana.dois@example.com", "Portugal");
        Author entity = new Author();
        entity.setId(1L);
        entity.setName("Ana Souza");
        entity.setGender(GenderEnum.FEMININO);
        entity.setEmail("ana@example.com");
        entity.setCpf("12345678901");
        entity.setCountryOrigin("Brazil");
        entity.setBirthDate("01-01-1980");

        mapper.updateFromRequest(request, entity);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Ana Dois", entity.getName());
        assertEquals("ana.dois@example.com", entity.getEmail());
        assertEquals("Portugal", entity.getCountryOrigin());
    }
}