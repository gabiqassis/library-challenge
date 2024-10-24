package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.response.PalindromeResponse;
import dev.gabiqassis.hering.service.PalindromeService;
import org.springframework.stereotype.Service;

import static java.util.stream.IntStream.range;


@Service
public class PalindromeImpl implements PalindromeService {

    @Override
    public PalindromeResponse isPalindrome(String word) {
        String cadeia = word.toLowerCase().replaceAll("\\s+", "");
        boolean palindromo = range(0, cadeia.length() / 2)
                .allMatch(i -> cadeia.charAt(i) == cadeia.charAt(cadeia.length() - 1 - i));
        return new PalindromeResponse(word, palindromo);
    }
}