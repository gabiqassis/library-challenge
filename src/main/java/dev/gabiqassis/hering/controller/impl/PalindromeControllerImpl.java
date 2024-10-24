package dev.gabiqassis.hering.controller.impl;

import dev.gabiqassis.hering.controller.PalindromeController;
import dev.gabiqassis.hering.domain.response.PalindromeResponse;
import dev.gabiqassis.hering.service.PalindromeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PalindromeControllerImpl implements PalindromeController {

    private final PalindromeService palindromeService;

    @Override
    public ResponseEntity<PalindromeResponse> isPalindrome(String word) {
        return ResponseEntity.ok(palindromeService.isPalindrome(word));
    }
}
