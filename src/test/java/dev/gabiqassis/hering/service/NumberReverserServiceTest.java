package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.response.ReversedResponse;
import dev.gabiqassis.hering.service.NumberReverserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NumberReverserServiceTest {

    @Autowired
    private NumberReverserService numberReverserService;

    @Test
    void testReverseNumber() {
        Integer inputNumber = 123;
        ReversedResponse response = numberReverserService.reverseNumber(inputNumber);

        assertNotNull(response);
        assertEquals(123, response.originalNumber());
        assertEquals(321, response.reversedNumber());
    }
}
