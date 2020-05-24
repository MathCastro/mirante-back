package com.example.mirante.person;

import com.example.mirante.operator.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById (Long id);

    @Query(value = "SELECT s FROM Person s WHERE s.name like %?1%",
            countQuery = "SELECT count(*) FROM Person s WHERE s.name like %?1%",
            nativeQuery = false)
    Page<Person> filter(@Param("value") String value, Pageable pageable);
}
