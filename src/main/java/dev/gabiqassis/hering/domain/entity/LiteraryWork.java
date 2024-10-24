package dev.gabiqassis.hering.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "obras literarias")
public class LiteraryWork {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate publicationDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate exhibitionDate;

    @ManyToMany
    @JoinTable(
            name = "autor_obra",
            joinColumns = @JoinColumn(name = "obra_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Author> authors;
}