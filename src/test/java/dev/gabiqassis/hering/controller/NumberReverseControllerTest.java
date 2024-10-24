package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.common.constants.PathConstants;
import dev.gabiqassis.hering.domain.response.ReversedResponse;
import dev.gabiqassis.hering.service.NumberReverserService;
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
class NumberReverseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private NumberReverserService numberReverserService;

    private MockMvc mockMvc;

    private ReversedResponse reversedResponse;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        reversedResponse = new ReversedResponse(123, 321);
    }

    @Test
    void testReverseNumber() throws Exception {
        Integer inputNumber = 123;

        Mockito.when(numberReverserService.reverseNumber(inputNumber)).thenReturn(reversedResponse);

        mockMvc.perform(get(PathConstants.NUMBER_REVERSER_V1)
                        .param("number", inputNumber.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.originalNumber").value(321))
                .andExpect(jsonPath("$.reversedNumber").value(123));

        Mockito.verify(numberReverserService).reverseNumber(inputNumber);
    }
}