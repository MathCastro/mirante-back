package com.example.mirante.phone;

import com.example.mirante.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findById (Long id);

    @Query(value = "SELECT s FROM Phone s WHERE s.number like %?1%",
            countQuery = "SELECT count(*) FROM Phone s WHERE s.number like %?1%",
            nativeQuery = false)
    Page<Phone> filter(@Param("value") String value, Pageable pageable);
}

