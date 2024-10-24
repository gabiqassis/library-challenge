package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.repository.CountryRepository;
import dev.gabiqassis.hering.service.impl.CountryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    private List<CountryDTO> countries;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countries = Arrays.asList(
                new CountryDTO("Brasileiro", "Brasil", "Brazil", "BR"),
                new CountryDTO("Argentino", "Argentina", "Argentina", "AR")
        );
    }

    @Test
    void testSearchCountryFound() {
        when(countryRepository.findAll()).thenReturn(countries);

        CountryDTO result = countryService.searchCountry("Brasil");

        assertNotNull(result);
        assertEquals("Brasil", result.nomePais());
    }

    @Test
    void testSearchCountryNotFound() {
        when(countryRepository.findAll()).thenReturn(countries);

        assertThrows(EntityNotFoundException.class, () -> countryService.searchCountry("Peru"));
    }
}
