package com.api.person.service;

import com.api.person.dto.request.PersonDTO;
import com.api.person.entity.Person;
import com.api.person.exceptions.PersonNotFoundException;
import com.api.person.mapper.PersonMapper;
import com.api.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private static final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.toDTO(savedPerson);
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll().stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException(id)
        );
    }

    public PersonDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.toDTO(savedPerson);
    }
}
