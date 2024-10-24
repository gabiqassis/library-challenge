package dev.gabiqassis.hering.controller;

import dev.gabiqassis.hering.domain.request.LiteraryWorkCreateRequest;
import dev.gabiqassis.hering.domain.request.LiteraryWorkUpdateRequest;
import dev.gabiqassis.hering.domain.response.AuthorResponse;
import dev.gabiqassis.hering.domain.response.LiteraryWorkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.gabiqassis.hering.common.constants.PathConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@RequestMapping(produces = APPLICATION_JSON_VALUE)
public interface LiteraryWorkController {

    @Operation(
            summary = "List all obras",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @GetMapping(OBRAS_V1)
    ResponseEntity<List<LiteraryWorkResponse>> findAll();

    @Operation(
            summary = "Create an obra",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @PostMapping(OBRAS_V1)
    ResponseEntity<LiteraryWorkResponse> create(@Validated @RequestBody LiteraryWorkCreateRequest obra);


    @Operation(
            summary = "Get an obra",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @GetMapping(OBRAS_ID)
    ResponseEntity<LiteraryWorkResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "List all authors by obra id",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @GetMapping(OBRAS_AUTORES)
    ResponseEntity<List<AuthorResponse>> findAutoresByObraId(@PathVariable Long id);

    @Operation(
            summary = "Update an obra",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @PutMapping(OBRAS_ID)
    ResponseEntity<LiteraryWorkResponse> update(@PathVariable Long id, @Valid @RequestBody LiteraryWorkUpdateRequest literaryWorkUpdateRequest);

    @Operation(
            summary = "Delete an obra",
            tags = {"obras"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content =
                            @Content(
                                    mediaType = APPLICATION_PROBLEM_JSON_VALUE,
                                    schema = @Schema(implementation = ProblemDetail.class)))
            })
    @DeleteMapping(OBRAS_ID)
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
