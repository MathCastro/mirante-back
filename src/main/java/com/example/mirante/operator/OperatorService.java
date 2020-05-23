package com.example.mirante.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {

    @Autowired
    OperatorRepository repository;

    public Page<Operator> getAll(Integer pageNo, Integer pageSize, String sortBy, String value) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Operator> pagedResult;

        if(value.equals("")) {
            pagedResult = repository.findAll(paging);
        } else {
            pagedResult = repository.filter(value.toLowerCase(), paging);
        }

        return pagedResult;
    }
}
