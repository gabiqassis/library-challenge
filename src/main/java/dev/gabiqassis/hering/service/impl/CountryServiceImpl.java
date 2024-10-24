package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.repository.CountryRepository;
import dev.gabiqassis.hering.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryDTO searchCountry(String termo) {
        List<CountryDTO> country = countryRepository.findAll();
        String normalizedTerm = normalize(termo);
        for (CountryDTO countries : country) {
            String normalizedCountryName = normalize(countries.nomePais());
            String normalizedIntlCountryName = normalize(countries.nomePaisInt());
            String normalizedAcronym = normalize(countries.sigla());
            if (normalizedCountryName.equals(normalizedTerm)
                    || normalizedIntlCountryName.equals(normalizedTerm)
                    || normalizedAcronym.equals(normalizedTerm)) {
                return countries;
            }
        }
        throw new EntityNotFoundException("País não encontrado");
    }

    private String normalize(String valor) {
        String normalized = Normalizer.normalize(valor, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[^\\p{ASCII}]", "")
                .trim()
                .toLowerCase()
                .replaceAll("\\s", "-");
        return normalized;
    }
}