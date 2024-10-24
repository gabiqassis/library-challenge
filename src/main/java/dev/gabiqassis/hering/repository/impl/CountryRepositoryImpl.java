package dev.gabiqassis.hering.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.repository.CountryRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private final List<CountryDTO> paises;

    public CountryRepositoryImpl() {
        this.paises = carregarPaises();
    }

    @Override
    public List<CountryDTO> findAll() {
        return paises;
    }

    private List<CountryDTO> carregarPaises() {
        ClassPathResource resource = new ClassPathResource("static/paises-gentilicos-google-maps.json");
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo de paises", e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo de paises", e);
        }
    }

}