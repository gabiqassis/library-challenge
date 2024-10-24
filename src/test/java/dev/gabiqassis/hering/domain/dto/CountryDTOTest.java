package dev.gabiqassis.hering.domain.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountryDTOTest {

    @Test
    public void testCountryDTOCreation() {
        CountryDTO country = new CountryDTO("Brasileiro", "Brasil", "Brazil", "BR");

        assertNotNull(country);
        assertEquals("Brasileiro", country.gentilico());
        assertEquals("Brasil", country.nomePais());
        assertEquals("Brazil", country.nomePaisInt());
        assertEquals("BR", country.sigla());
    }

    @Test
    public void testCountryDTOJsonSerialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CountryDTO country = new CountryDTO("Brasileiro", "Brasil", "Brazil", "BR");

        String json = objectMapper.writeValueAsString(country);
        assertNotNull(json);
        assertTrue(json.contains("Brasileiro"));
        assertTrue(json.contains("Brasil"));
        assertTrue(json.contains("Brazil"));
        assertTrue(json.contains("BR"));
    }

    @Test
    public void testCountryDTOJsonDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"gentilico\": \"Brasileiro\", \"nome_pais\": \"Brasil\", \"nome_pais_int\": \"Brazil\", \"sigla\": \"BR\" }";

        CountryDTO country = objectMapper.readValue(json, CountryDTO.class);

        assertNotNull(country);
        assertEquals("Brasileiro", country.gentilico());
        assertEquals("Brasil", country.nomePais());
        assertEquals("Brazil", country.nomePaisInt());
        assertEquals("BR", country.sigla());
    }

    public class LiteraryWorkTest {

        @Test
        void createLiteraryWork() {
            LiteraryWork work = new LiteraryWork();
            work.setId(1L);
            work.setName("O Alquimista");
            work.setDescription("Uma incrível jornada de autoconhecimento.");
            work.setPublicationDate(LocalDate.of(1988, 4, 1));
            work.setExhibitionDate(LocalDate.of(1988, 7, 1));
            work.setAuthors(new ArrayList<>());

            assertNotNull(work);
            assertEquals(1L, work.getId());
            assertEquals("O Alquimista", work.getName());
            assertEquals("Uma incrível jornada de autoconhecimento.", work.getDescription());
            assertEquals(LocalDate.of(1988, 4, 1), work.getPublicationDate());
            assertEquals(LocalDate.of(1988, 7, 1), work.getExhibitionDate());
            assertNotNull(work.getAuthors());
            assertTrue(work.getAuthors().isEmpty());
        }

        @Test
        void literaryWorkToString() {
            LiteraryWork work = new LiteraryWork();
            work.setId(1L);
            work.setName("O Alquimista");
            work.setDescription("Uma incrível jornada de autoconhecimento.");
            work.setPublicationDate(LocalDate.of(1988, 4, 1));
            work.setExhibitionDate(LocalDate.of(1988, 7, 1));
            work.setAuthors(new ArrayList<>());

            String workString = work.toString();

            assertNotNull(workString);
            assertTrue(workString.contains("O Alquimista"));
            assertTrue(workString.contains("Uma incrível jornada de autoconhecimento."));
            assertTrue(workString.contains("1988-04-01"));
            assertTrue(workString.contains("1988-07-01"));
        }

        @Test
        void jsonSerialization() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            LiteraryWork work = new LiteraryWork();
            work.setId(1L);
            work.setName("O Alquimista");
            work.setDescription("Uma incrível jornada de autoconhecimento.");
            work.setPublicationDate(LocalDate.of(1988, 4, 1));
            work.setExhibitionDate(LocalDate.of(1988, 7, 1));
            work.setAuthors(new ArrayList<>());

            String json = objectMapper.writeValueAsString(work);
            assertNotNull(json);
            assertTrue(json.contains("O Alquimista"));
            assertTrue(json.contains("Uma incrível jornada de autoconhecimento."));
            assertTrue(json.contains("1988-04-01"));
            assertTrue(json.contains("1988-07-01"));
        }

        @Test
        void jsonDeserialization() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = "{ \"id\": 1, \"name\": \"O Alquimista\", \"description\": \"Uma incrível jornada de autoconhecimento.\", \"publicationDate\": \"1988-04-01\", \"exhibitionDate\": \"1988-07-01\" }";

            LiteraryWork work = objectMapper.readValue(json, LiteraryWork.class);

            assertNotNull(work);
            assertEquals(1L, work.getId());
            assertEquals("O Alquimista", work.getName());
            assertEquals("Uma incrível jornada de autoconhecimento.", work.getDescription());
            assertEquals(LocalDate.of(1988, 4, 1), work.getPublicationDate());
            assertEquals(LocalDate.of(1988, 7, 1), work.getExhibitionDate());
        }
    }
}
