package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.response.PalindromeResponse;

public interface PalindromeService {
    PalindromeResponse isPalindrome(String word);
}
