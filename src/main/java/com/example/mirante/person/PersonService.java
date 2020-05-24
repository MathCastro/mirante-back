package com.example.mirante.person;

import com.example.mirante.operator.Operator;
import com.example.mirante.operator.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Page<Person> getAll(Integer pageNo, Integer pageSize, String sortBy, String value) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Person> pagedResult;

        if(value.equals("")) {
            pagedResult = repository.findAll(paging);
        } else {
            pagedResult = repository.filter(value.toLowerCase(), paging);
        }

        return pagedResult;
    }
}
