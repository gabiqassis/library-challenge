package dev.gabiqassis.hering.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "autor_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private String birthDate;

    @Column(unique = true)
    private String email;

    @Column(unique = true, length = 11)
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private String countryOrigin;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private List<LiteraryWork> literaryWorks;
}