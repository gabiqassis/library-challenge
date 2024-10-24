package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.domain.enums.GenderEnum;
import dev.gabiqassis.hering.domain.request.AuthorUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import dev.gabiqassis.hering.service.AuthorService;
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

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private AuthorResponse authorResponse;
    private LiteraryWorkResponse literaryWorkResponse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        authorResponse = new AuthorResponse(1L, "João", GenderEnum.MASCULINO, "01-01-2020", "joao@example.com", "12345678901", "Brasil");

        literaryWorkResponse = new LiteraryWorkResponse(1L, "Jogos Vorazes", "Obra de ficção", LocalDate.of(2020, 1, 1), LocalDate.of(1980, 1, 1));
    }

    @Test
    void testFindAll() throws Exception {
        Mockito.when(authorService.findAll()).thenReturn(Collections.singletonList(authorResponse));

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("João"));
    }

    @Test
    void testCreate() throws Exception {
        Mockito.when(authorService.create(any())).thenReturn(authorResponse);

        String authorJson = """
        {"name": "João",
          "gender": "MASCULINO",
          "email": "joao.silva@example.com",
          "birthDate": "01-01-2020",
          "countryOrigin": "Brasil",
          "cpf": "12345678901"
        }""";

        mockMvc.perform(post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João"));
    }


    @Test
    void testFindById() throws Exception {
        Mockito.when(authorService.findById(Mockito.anyLong())).thenReturn(authorResponse);

        mockMvc.perform(get("/api/v1/authors/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João"));
    }

    @Test
    void testFindObrasByAutorId() throws Exception {
        Mockito.when(authorService.findObrasByAutorId(Mockito.anyLong())).thenReturn(Collections.singletonList(literaryWorkResponse));

        mockMvc.perform(get("/api/v1/authors/{id}/literarywork", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Jogos Vorazes"));
    }

    @Test
    void testUpdate() throws Exception {
        Long authorId = 1L;
        Mockito.when(authorService.update(eq(authorId), any(AuthorUpdateRequest.class))).thenReturn(new AuthorResponse(1L, "João Atualizado", GenderEnum.MASCULINO, "01-01-2020", "joao.atualizado@example.com", "12345678901", "Brasil"));

        String updateJson = """
            {"name": "João Atualizado",
              "email": "joao.atualizado@example.com",
              "countryOrigin": "Brasil"
            }""";

        mockMvc.perform(put("/api/v1/authors/" + authorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("João Atualizado"));
    }

    @Test
    void testDeleteById() throws Exception {
        Mockito.doNothing().when(authorService).deleteById(Mockito.anyLong());

        mockMvc.perform(delete("/api/v1/authors/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
