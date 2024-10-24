package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.entity.Author;
import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import dev.gabiqassis.hering.domain.mapper.AuthorMapper;
import dev.gabiqassis.hering.domain.mapper.LiteraryWorkMapper;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.repository.AuthorRepository;
import dev.gabiqassis.hering.repository.LiteraryWorkRepository;
import dev.gabiqassis.hering.service.impl.LiteraryWorkServiceImpl;
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

class LiteraryWorkServiceImplTest {

    @Mock
    private LiteraryWorkRepository literaryWorkRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private LiteraryWorkMapper literaryWorkMapper;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private LiteraryWorkServiceImpl literaryWorkService;

    private LiteraryWork existingLiteraryWork;
    private LiteraryWorkUpdateRequest literaryWorkUpdateRequest;
    private LiteraryWorkResponse updatedLiteraryWorkResponse;
    private Author existingAuthor;
    private AuthorResponse authorResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        existingAuthor = new Author();
        existingAuthor.setId(1L);
        existingAuthor.setName("Original Author");

        existingLiteraryWork = new LiteraryWork();
        existingLiteraryWork.setId(1L);
        existingLiteraryWork.setName("Original Work");
        existingLiteraryWork.setDescription("Original Description");
        existingLiteraryWork.setPublicationDate(LocalDate.of(2024, 10, 1));
        existingLiteraryWork.setExhibitionDate(LocalDate.of(2024, 10, 1));
        existingLiteraryWork.setAuthors(Collections.singletonList(existingAuthor));

        literaryWorkUpdateRequest = new LiteraryWorkUpdateRequest("Updated Work", "Updated Description", LocalDate.of(2024, 10, 2));

        updatedLiteraryWorkResponse = new LiteraryWorkResponse(1L, "Updated Work", "Updated Description", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 2));

        authorResponse = new AuthorResponse(1L, "Original Author", GenderEnum.MASCULINO, "01-01-1980", "original@example.com", "12345678901", "Brasil");
    }

    @Test
    void testUpdateLiteraryWorkNotFound() {
        when(literaryWorkRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> literaryWorkService.update(1L, literaryWorkUpdateRequest));
    }

    @Test
    void testFindAutoresByObraId() {
        when(literaryWorkRepository.findById(1L)).thenReturn(Optional.of(existingLiteraryWork));
        when(authorMapper.map(any(Author.class))).thenReturn(authorResponse);

        List<AuthorResponse> result = literaryWorkService.findAutoresByObraId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Original Author", result.get(0).name());
    }

    @Test
    void testFindAutoresByObraIdNotFound() {
        when(literaryWorkRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> literaryWorkService.findAutoresByObraId(1L));
    }
}
