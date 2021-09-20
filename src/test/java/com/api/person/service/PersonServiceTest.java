package com.api.person.service;

import com.api.person.dto.request.PersonDTO;
import com.api.person.entity.Person;
import com.api.person.repository.PersonRepository;
import com.api.person.utils.PersonUtils;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void TestGivenPersonDtoThenReturnSavedPerson() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person personSaved = PersonUtils.createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(personSaved);

       // personDTO.setId(personSaved.getId());

       PersonDTO personDTOSaved = personService.createPerson(personDTO);

        assertNotNull(personDTOSaved);

    }

}