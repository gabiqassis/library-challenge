package dev.gabiqassis.hering.controller.impl;

import dev.gabiqassis.hering.controller.NumberReverserController;
import dev.gabiqassis.hering.domain.response.ReversedResponse;
import dev.gabiqassis.hering.service.NumberReverserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberReverserControllerImpl implements NumberReverserController {

    private final NumberReverserService numberReverserService;

    @Override
    public ResponseEntity<ReversedResponse> reverseNumber(Integer number) {
        return ResponseEntity.ok(numberReverserService.reverseNumber(number));
    }
}
