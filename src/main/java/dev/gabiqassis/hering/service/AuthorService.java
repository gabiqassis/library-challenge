package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.request.AuthorCreateRequest;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService {
    void deleteById(Long id);

    AuthorResponse findById(Long id);

    AuthorResponse create(AuthorCreateRequest authorCreateRequest);

    AuthorResponse update(Long id, AuthorUpdateRequest authorUpdateRequest);

    List<AuthorResponse> findAll();

    @Transactional
    List<LiteraryWorkResponse> findObrasByAutorId(Long id);
}
