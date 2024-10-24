package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.entity.Author;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final CountryService countryService;
    private final AuthorRepository autHorRepository;
    private final AuthorMapper authorMapper;
    private final LiteraryWorkMapper literaryWorkMapper;

    @Override
    public AuthorResponse create(AuthorCreateRequest authorCreateRequest) {
        if(authorCreateRequest.birthDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("A data não pode ser futura");
        }

        Author author = authorMapper.map(authorCreateRequest);

        String country = countryService.searchCountry(author.getCountryOrigin()).nomePais();

        if (country.equalsIgnoreCase("Brasil")) {
            String validaredCPF = ValidationCpf.validateCPF(authorCreateRequest.cpf());
            author.setCpf(validaredCPF);
        } else {
            author.setCpf(null);
        }

        author.setCountryOrigin(country);
        return authorMapper.map(autHorRepository.save(author));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorResponse> findAll() {
        return autHorRepository.findAll()
                .stream()
                .map(authorMapper::map)
                .toList();
    }

    private Author findAutorById(Long id) {
        return autHorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorResponse findById(Long id) {
        Author author = findAutorById(id);
        return authorMapper.map(author);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        autHorRepository.findById(id)
                .ifPresentOrElse(autHorRepository::delete, () -> {
                            throw new EntityNotFoundException("Autor não encontrado");
                        }
                );
    }

    @Transactional
    @Override
    public AuthorResponse update(Long id, AuthorUpdateRequest autorAtualizado) {
        Author autorExistente = this.findAutorById(id);
        authorMapper.updateFromRequest(autorAtualizado, autorExistente);
        return authorMapper.map(autHorRepository.save(autorExistente));
    }

    @Transactional
    @Override
    public List<LiteraryWorkResponse> findObrasByAutorId(Long id) {
        Author autor = findAutorById(id);
        return autor.getLiteraryWorks()
                .stream()
                .map(literaryWorkMapper::map)
                .toList();
    }

}
