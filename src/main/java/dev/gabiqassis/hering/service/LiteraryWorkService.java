package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LiteraryWorkService {
    List<LiteraryWorkResponse> findAll();

    LiteraryWorkResponse create(LiteraryWorkCreateRequest literaryWorkCreateRequest);

    LiteraryWorkResponse findById(Long id);

    LiteraryWorkResponse update(Long id, LiteraryWorkUpdateRequest literaryWorkUpdateRequest);

    void deleteById(Long id);

    @Transactional
    List<AuthorResponse> findAutoresByObraId(Long id);
}
