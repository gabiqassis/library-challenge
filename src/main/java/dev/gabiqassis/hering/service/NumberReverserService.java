package dev.gabiqassis.hering.service;


import dev.gabiqassis.hering.domain.response.ReversedResponse;

public interface NumberReverserService {
    ReversedResponse reverseNumber(Integer number);
}