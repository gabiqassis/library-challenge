package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.response.PalindromeResponse;
import dev.gabiqassis.hering.service.PalindromeService;
import org.springframework.stereotype.Service;

import static java.util.stream.IntStream.range;


@Service
public class PalindromeImpl implements PalindromeService {

    @Override
    public PalindromeResponse isPalindrome(String word) {
        String chain = word.toLowerCase().replaceAll("\\s+", "");
        boolean isPalindromo = range(0, chain.length() / 2)
                .allMatch(i -> chain.charAt(i) == chain.charAt(chain.length() - 1 - i));
        return new PalindromeResponse(word, isPalindromo);
    }
}