package com.example.mirante.operator;

import com.example.mirante.security.controller.AuthorizationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private AuthorizationController authorizationController;

    @GetMapping
    public ResponseEntity<Page<Operator>> filter(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String value) {

        return new ResponseEntity<>(operatorService.getAll(pageNo, pageSize, sortBy, value), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operator> get(@PathVariable Long id) {
        Optional<Operator> operator = operatorRepository.findById(id);

        if (operator == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(operator.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Operator create(@Valid @RequestBody Operator operator) {
        authorizationController.create(operator.getUser());

        operator.setCreationDate(new Date());

        return operatorRepository.save(operator);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Operator> delete(@PathVariable Long id) {
        operatorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
