package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.common.constants.PathConstants;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.service.LiteraryWorkService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
class LiteraryWorkControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LiteraryWorkService literaryWorkService;

    private MockMvc mockMvc;

    private LiteraryWorkResponse literaryWorkResponse;
    private LiteraryWorkCreateRequest literaryWorkCreateRequest;
    private LiteraryWorkUpdateRequest literaryWorkUpdateRequest;
    private AuthorResponse authorResponse;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        literaryWorkResponse = new LiteraryWorkResponse(1L, "Jogos Vorazes", "Descrição da obra", LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 10, 1));
        literaryWorkCreateRequest = new LiteraryWorkCreateRequest("Jogos Vorazes", "Descrição da obra", LocalDate.of(2024, 10, 1),
                LocalDate.of(2024, 10, 1), Arrays.asList(1L));
        literaryWorkUpdateRequest = new LiteraryWorkUpdateRequest(
                "Jogos Vorazes Atualizado", "Descrição Atualizada da obra", LocalDate.of(2024, 10, 1));
        authorResponse = new AuthorResponse(1L, "João Silva", GenderEnum.MASCULINO, "01-01-1980", "joao.silva@example.com",
                "12345678901", "Brasil");
    }

    @Test
    void testFindAll() throws Exception {
        Mockito.when(literaryWorkService.findAll()).thenReturn(Collections.singletonList(literaryWorkResponse));

        mockMvc.perform(get(PathConstants.LITERARY_WORK_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Jogos Vorazes"));
    }

    @Test
    void testFindById() throws Exception {
        Mockito.when(literaryWorkService.findById(Mockito.anyLong())).thenReturn(literaryWorkResponse);

        mockMvc.perform(get(PathConstants.LITERARY_WORK_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Jogos Vorazes"));
    }

    @Test
    void testFindAutoresByObraId() throws Exception {
        Mockito.when(literaryWorkService.findAutoresByObraId(Mockito.anyLong())).thenReturn(Collections.singletonList(authorResponse));

        mockMvc.perform(get(PathConstants.LITERARY_WORK_AUTHORS, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("João Silva"));
    }

    @Test
    void testDeleteById() throws Exception {
        Mockito.doNothing().when(literaryWorkService).deleteById(Mockito.anyLong());

        mockMvc.perform(delete(PathConstants.LITERARY_WORK_ID, 1L))
                .andExpect(status().isNoContent());
    }
}

