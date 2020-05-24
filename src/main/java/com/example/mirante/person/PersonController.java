package com.example.mirante.person;

import com.example.mirante.phone.Phone;
import com.example.mirante.phone.PhoneController;
import com.example.mirante.security.model.User;
import com.example.mirante.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    PhoneController phoneController;

    @GetMapping
    public ResponseEntity<Page<Person>> filter(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String value) {

        return new ResponseEntity<>(personService.getAll(pageNo, pageSize, sortBy, value), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(person.get());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@Valid @RequestBody Person person, Authentication authentication) {
        Optional<User> user = userRepository.findByUsername(authentication.getName());

        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User not authenticated");
        }

        for (Phone phone:
             person.getPhones()) {
            phone.setOperator(user.get().getOperator());
        }

        person.setOperator(user.get().getOperator());
        person.setPhones(phoneController.createAll(person.getPhones()));

        return personRepository.save(person);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Person> update(@Valid @RequestBody Person person) {
        Optional<Person> oldPerson = personRepository.findById(person.getId());

        if (oldPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            for (Phone phone:
                    person.getPhones()) {
                phone.setOperator(oldPerson.get().getOperator());
            }
            person.setPhones(phoneController.createAll(person.getPhones()));
            person.setOperator(oldPerson.get().getOperator());
            personRepository.save(person);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> delete(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
