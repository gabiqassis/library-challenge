package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.domain.entity.Author;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import dev.gabiqassis.hering.domain.mapper.AuthorMapper;
import dev.gabiqassis.hering.domain.mapper.LiteraryWorkMapper;
import dev.gabiqassis.hering.domain.request.AuthorCreateRequest;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.domain.util.ValidationCpf;
import dev.gabiqassis.hering.repository.AuthorRepository;
import dev.gabiqassis.hering.service.AuthorService;
import dev.gabiqassis.hering.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private CountryService countryService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private LiteraryWorkMapper literaryWorkMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorCreateRequest authorCreateRequest;
    private AuthorUpdateRequest authorUpdateRequest;
    private AuthorResponse authorResponse;
    private LiteraryWorkResponse literaryWorkResponse;
    private Author existingAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author();
        author.setId(1L);
        author.setName("João Silva");
        author.setGender(GenderEnum.MASCULINO);
        author.setBirthDate("01-01-1980");
        author.setEmail("joao.silva@example.com");
        author.setCpf("12345678901");
        author.setCountryOrigin("Brasil");

        authorCreateRequest = new AuthorCreateRequest("João Silva", GenderEnum.MASCULINO, "joao.silva@example.com", LocalDate.of(1980, 1, 1), "Brasil", "51390103056");
        authorUpdateRequest = new AuthorUpdateRequest("João Silva Atualizado", "joao.silva.atualizado@example.com", "Brasil");
        authorResponse = new AuthorResponse(1L, "João Silva", GenderEnum.MASCULINO, "01-01-1980", "joao.silva@example.com", "51390103056", "Brasil");
        literaryWorkResponse = new LiteraryWorkResponse(1L, "Jogos Vorazes", "Obra Literária", LocalDate.now(), LocalDate.now());
    }

    @Test
    void testCreate() {
        when(countryService.searchCountry(anyString())).thenReturn(new CountryDTO("Brasileiro", "Brasil", "Brazil", "BR"));
        when(authorMapper.map(any(AuthorCreateRequest.class))).thenReturn(author);
        when(authorMapper.map(any(Author.class))).thenReturn(authorResponse);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorResponse response = authorService.create(authorCreateRequest);

        assertNotNull(response);
        assertEquals("João Silva", response.name());
        assertEquals("Brasil", response.countryOrigin());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testFindAll() {
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));
        when(authorMapper.map(any(Author.class))).thenReturn(authorResponse);

        List<AuthorResponse> responses = authorService.findAll();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(authorMapper.map(any(Author.class))).thenReturn(authorResponse);

        AuthorResponse response = authorService.findById(1L);

        assertNotNull(response);
        assertEquals("João Silva", response.name());
        verify(authorRepository, times(1)).findById(anyLong());
    }

    @Test
    void testDeleteById() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));

        authorService.deleteById(1L);

        verify(authorRepository, times(1)).delete(any(Author.class));
    }

    @Test
    void testUpdateAuthorNotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authorService.update(1L, authorUpdateRequest));
    }


    @Test
    void testFindObrasByAutorIdNotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authorService.findObrasByAutorId(1L));
    }
}
