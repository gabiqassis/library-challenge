package dev.gabiqassis.hering.domain.mapper;

import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiteraryWorkMapperTest {

    @Autowired
    private LiteraryWorkMapper mapper;

    @Test
    void testMapCreateRequestToEntity() {
        List<Long> authors = new ArrayList<>();
        authors.add(1L);
        LiteraryWorkCreateRequest request = new LiteraryWorkCreateRequest("Jogos Vorazes", "Obra de ficção", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 1), authors);

        LiteraryWork entity = mapper.map(request);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertEquals("Jogos Vorazes", entity.getName());
        assertEquals("Obra de ficção", entity.getDescription());
        assertEquals(LocalDate.of(2024, 10, 1), entity.getPublicationDate());
        assertEquals(LocalDate.of(2024, 10, 1), entity.getExhibitionDate());
        assertNull(entity.getAuthors());
    }

    @Test
    void testMapEntityToResponse() {
        LiteraryWork entity = new LiteraryWork();
        entity.setId(1L);
        entity.setName("Jogos Vorazes");
        entity.setDescription("Obra de ficção");
        entity.setPublicationDate(LocalDate.of(2024, 10, 1));
        entity.setExhibitionDate(LocalDate.of(2024, 10, 1));
        entity.setAuthors(Collections.emptyList());

        LiteraryWorkResponse response = mapper.map(entity);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Jogos Vorazes", response.name());
        assertEquals("Obra de ficção", response.description());
    }

    @Test
    void testMapUpdateRequestToEntity() {
        LiteraryWorkUpdateRequest request = new LiteraryWorkUpdateRequest("Jogos Vorazes", "Obra de ficção", LocalDate.of(2024, 10, 1));
        LiteraryWork entity = new LiteraryWork();
        entity.setId(1L);
        entity.setName("Jogos Vorazes");
        entity.setDescription("Obra de ficção");
        entity.setPublicationDate(LocalDate.of(2024, 10, 1));
        entity.setExhibitionDate(LocalDate.of(2024, 10, 1));
        mapper.map(request, entity);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Jogos Vorazes", entity.getName());
        assertEquals("Obra de ficção", entity.getDescription());
        assertEquals(LocalDate.of(2024, 10, 1), entity.getExhibitionDate());
        assertNotNull(entity.getPublicationDate());
    }
}

