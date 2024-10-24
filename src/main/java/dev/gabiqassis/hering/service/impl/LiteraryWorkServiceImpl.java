package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.entity.Author;
import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import dev.gabiqassis.hering.domain.mapper.AuthorMapper;
import dev.gabiqassis.hering.domain.mapper.LiteraryWorkMapper;
import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.repository.AuthorRepository;
import dev.gabiqassis.hering.repository.LiteraryWorkRepository;
import dev.gabiqassis.hering.service.LiteraryWorkService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class LiteraryWorkServiceImpl implements LiteraryWorkService {
    private final LiteraryWorkMapper literaryWorkMapper;
    private final AuthorMapper authorMapper;
    private final LiteraryWorkRepository literaryWorkRepository;
    private final AuthorRepository autHorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<LiteraryWorkResponse> findAll() {
        return literaryWorkRepository.findAll()
                .stream()
                .map(literaryWorkMapper::map)
                .toList();
    }
    @Transactional
    @Override
    public LiteraryWorkResponse create(LiteraryWorkCreateRequest literaryWorkCreateRequest) {
        if (literaryWorkCreateRequest.publicationDate() == null && literaryWorkCreateRequest.exhibitionDate() == null) {
            throw new IllegalArgumentException("Data de publicação e data de exposição não podem ser nulas");
        }

        List<Author> autores = new ArrayList<>();

        literaryWorkCreateRequest.authors()
                .forEach(autor -> autores.add(autHorRepository.findById(autor)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Autor com id %s não encontrado",autor)))));

        LiteraryWork literaryWork = literaryWorkMapper.map(literaryWorkCreateRequest);
        literaryWork.setAuthors(autores);
        return literaryWorkMapper.map(literaryWorkRepository.save(literaryWork));
    }

    private LiteraryWork findObraById(Long id) {
        return literaryWorkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Obra não encontrada"));
    }

    @Transactional(readOnly = true)
    @Override
    public LiteraryWorkResponse findById(Long id) {
        LiteraryWork literaryWork = findObraById(id);
        return literaryWorkMapper.map(literaryWork);
    }

    @Transactional
    @Override
    public LiteraryWorkResponse update(Long id, LiteraryWorkUpdateRequest literaryWorkUpdateRequest) {
        LiteraryWork literaryWork = findObraById(id);
        literaryWorkMapper.map(literaryWorkUpdateRequest, literaryWork);
        literaryWork.setId(id);
        return literaryWorkMapper.map(literaryWorkRepository.save(literaryWork));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        literaryWorkRepository.findById(id)
                .ifPresentOrElse(literaryWorkRepository::delete, () -> {
                            throw new EntityNotFoundException("Obra não encontrada");
                        }
                );
    }

    @Transactional
    @Override
    public List<AuthorResponse> findAutoresByObraId(Long id) {
        LiteraryWork literaryWork = findObraById(id);
        return literaryWork.getAuthors()
                .stream()
                .map(authorMapper::map)
                .toList();
    }
}
