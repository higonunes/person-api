package com.api.person.controller;

import com.api.person.dto.request.PersonDTO;
import com.api.person.exceptions.PersonNotFoundException;
import com.api.person.repository.PersonRepository;
import com.api.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PersonDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping
    List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PersonDTO updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @GetMapping("/{id}")
    PersonDTO findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
