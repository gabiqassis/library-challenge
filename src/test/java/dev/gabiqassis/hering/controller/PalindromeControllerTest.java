package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.common.constants.PathConstants;
import dev.gabiqassis.hering.domain.response.PalindromeResponse;
import dev.gabiqassis.hering.service.PalindromeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PalindromeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PalindromeService palindromeService;

    private MockMvc mockMvc;

    private PalindromeResponse palindromeResponse;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        palindromeResponse = new PalindromeResponse("madam", true);
    }

    @Test
    void testIsPalindrome() throws Exception {
        String inputWord = "madam";

        Mockito.when(palindromeService.isPalindrome(inputWord)).thenReturn(palindromeResponse);

        mockMvc.perform(get(PathConstants.PALINDROME_V1)
                        .param("word", inputWord)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.word").value("madam"))
                .andExpect(jsonPath("$.isPalindrome").value(true));

        Mockito.verify(palindromeService).isPalindrome(inputWord);
    }
}
