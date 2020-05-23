package com.example.mirante.operator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long>  {

    Optional<Operator> findById (Long id);
}
