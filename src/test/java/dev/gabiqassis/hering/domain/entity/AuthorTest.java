package dev.gabiqassis.hering.domain.entity;

import dev.gabiqassis.hering.domain.enums.GenderEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Ana Souza");
        author.setGender(GenderEnum.FEMININO);
        author.setBirthDate("10-12-1975");
        author.setEmail("ana.souza@example.com");
        author.setCpf("98765432100");
        author.setCountryOrigin("Portugal");
        author.setLiteraryWorks(new ArrayList<>());

        assertNotNull(author);
        assertEquals(1L, author.getId());
        assertEquals("Ana Souza", author.getName());
        assertEquals(GenderEnum.FEMININO, author.getGender());
        assertEquals("10-12-1975", author.getBirthDate());
        assertEquals("ana.souza@example.com", author.getEmail());
        assertEquals("98765432100", author.getCpf());
        assertEquals("Portugal", author.getCountryOrigin());
        assertNotNull(author.getLiteraryWorks());
        assertTrue(author.getLiteraryWorks().isEmpty());
    }

    @Test
    public void testAuthorToString() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Ana Souza");
        author.setGender(GenderEnum.FEMININO);
        author.setBirthDate("10-12-1975");
        author.setEmail("ana.souza@example.com");
        author.setCpf("98765432100");
        author.setCountryOrigin("Portugal");
        author.setLiteraryWorks(new ArrayList<>());

        String authorString = author.toString();

        assertNotNull(authorString);
        assertTrue(authorString.contains("Ana Souza"));
        assertTrue(authorString.contains("FEMININO"));
        assertTrue(authorString.contains("10-12-1975"));
        assertTrue(authorString.contains("ana.souza@example.com"));
        assertTrue(authorString.contains("98765432100"));
        assertTrue(authorString.contains("Portugal"));
    }
}

