package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.common.constants.PathConstants;
import dev.gabiqassis.hering.domain.dto.CountryDTO;
import dev.gabiqassis.hering.domain.response.CountryResponse;
import dev.gabiqassis.hering.service.CountryService;
import dev.gabiqassis.hering.domain.mapper.CountryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CountryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @MockBean
    private CountryMapper countryMapper;

    private CountryResponse countryResponse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        countryResponse = new CountryResponse("Brasileiro", "Brasil", "Brazil", "BR");
    }

    @Test
    void testSearchCountries() throws Exception {
        String searchTerm = "Brazil";
        Mockito.when(countryService.searchCountry(searchTerm)).thenReturn(new CountryDTO("Brasileiro", "Brasil", "Brazil", "BR"));
        Mockito.when(countryMapper.map(Mockito.any())).thenReturn(countryResponse);

        mockMvc.perform(get(PathConstants.SEARCH_COUTRY)
                        .param("term", searchTerm)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.gentilico").value("Brasileiro"))
                .andExpect(jsonPath("$.nome_pais").value("Brasil"))
                .andExpect(jsonPath("$.nome_pais_int").value("Brazil"))
                .andExpect(jsonPath("$.sigla").value("BR"));
    }
}




