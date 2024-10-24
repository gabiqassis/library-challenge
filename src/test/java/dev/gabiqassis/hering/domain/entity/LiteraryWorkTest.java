package dev.gabiqassis.hering.domain.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LiteraryWorkTest {

    @Test
    public void testLiteraryWorkCreation() {
        LiteraryWork work = new LiteraryWork();
        work.setId(1L);
        work.setName("Jogos vorazes");
        work.setDescription("Uma obra de ficção.");
        work.setPublicationDate(LocalDate.of(2024, 10, 1));
        work.setExhibitionDate(LocalDate.of(2024, 10, 1));
        work.setAuthors(new ArrayList<>());

        assertNotNull(work);
        assertEquals(1L, work.getId());
        assertEquals("Jogos vorazes", work.getName());
        assertEquals("Uma obra de ficção.", work.getDescription());
        assertEquals(LocalDate.of(2024, 10, 1), work.getPublicationDate());
        assertEquals(LocalDate.of(2024, 10, 1), work.getExhibitionDate());
        assertNotNull(work.getAuthors());
        assertTrue(work.getAuthors().isEmpty());
    }

}

