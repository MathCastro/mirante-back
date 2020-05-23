package com.example.mirante.operator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long>  {

    Optional<Operator> findById (Long id);

    @Query(value = "SELECT s FROM Operator s WHERE s.name like %?1%",
            countQuery = "SELECT count(*) FROM Operator s WHERE s.name like %?1%",
            nativeQuery = false)
    Page<Operator> filter(@Param("value") String value, Pageable pageable);
}
