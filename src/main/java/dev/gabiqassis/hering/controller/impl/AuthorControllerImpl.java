package dev.gabiqassis.hering.controller.impl;

import dev.gabiqassis.hering.controller.AuthorController;
import dev.gabiqassis.hering.domain.request.AuthorCreateRequest;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    public ResponseEntity<List<AuthorResponse>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Override
    public ResponseEntity<AuthorResponse> create(AuthorCreateRequest authorCreateRequest) {
        return ResponseEntity.status(CREATED).body(authorService.create(authorCreateRequest));
    }

    @Override
    public ResponseEntity<AuthorResponse> findById(Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @Override
    public ResponseEntity<List<LiteraryWorkResponse>> findObrasByAutorId(Long id) {
        return ResponseEntity.ok(authorService.findObrasByAutorId(id));
    }

    @Override
    public ResponseEntity<AuthorResponse> update(Long id, AuthorUpdateRequest autorCreateRequest) {
        return ResponseEntity.ok(authorService.update(id, autorCreateRequest));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}