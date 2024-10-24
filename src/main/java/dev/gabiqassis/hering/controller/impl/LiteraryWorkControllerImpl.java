package dev.gabiqassis.hering.controller.impl;

import dev.gabiqassis.hering.controller.LiteraryWorkController;
import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.service.LiteraryWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LiteraryWorkControllerImpl implements LiteraryWorkController {

    private final LiteraryWorkService literaryWorkService;

    @Override
    public ResponseEntity<List<LiteraryWorkResponse>> findAll() {
        return ResponseEntity.ok(literaryWorkService.findAll());
    }

    @Override
    public ResponseEntity<LiteraryWorkResponse> create(LiteraryWorkCreateRequest literaryWorkCreateRequest) {
        return ResponseEntity.ok(literaryWorkService.create(literaryWorkCreateRequest));
    }

    @Override
    public ResponseEntity<LiteraryWorkResponse> findById(Long id) {
        return ResponseEntity.ok(literaryWorkService.findById(id));
    }

    @Override
    public ResponseEntity<List<AuthorResponse>> findAutoresByObraId(Long id) {
        return ResponseEntity.ok(literaryWorkService.findAutoresByObraId(id));
    }

    @Override
    public ResponseEntity<LiteraryWorkResponse> update(Long id, LiteraryWorkUpdateRequest obraAtualizada) {
        return ResponseEntity.ok(literaryWorkService.update(id, obraAtualizada));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        literaryWorkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}