package com.example.mirante.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping
    public List<Phone> list() {
        return phoneRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phone> get(@PathVariable Long id) {
        Optional<Phone> phone = phoneRepository.findById(id);

        if (phone == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(phone.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Phone create(@Valid @RequestBody Phone phone) {
        return phoneRepository.save(phone);
    }

    public List<Phone> createAll(@Valid @RequestBody List<Phone> phones) {
        return phoneRepository.saveAll(phones);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Phone> update(@Valid @RequestBody Phone phone) {
        Optional<Phone> oldPhone = phoneRepository.findById(phone.getId());

        if (oldPhone == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.phoneRepository.save(phone);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Phone> delete(@PathVariable Long id) {
        phoneRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
