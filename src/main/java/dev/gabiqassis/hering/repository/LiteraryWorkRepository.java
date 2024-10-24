package dev.gabiqassis.hering.repository;

import dev.gabiqassis.hering.domain.entity.LiteraryWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteraryWorkRepository extends JpaRepository<LiteraryWork, Long> {
}