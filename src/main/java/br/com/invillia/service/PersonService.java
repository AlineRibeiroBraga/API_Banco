package br.com.invillia.service;

import br.com.invillia.domain.Person;
import br.com.invillia.domain.request.PersonRequest;
import br.com.invillia.exception.personException.CpfAlreadyExistsException;
import br.com.invillia.exception.personException.PersonNotFoundException;
import br.com.invillia.mapper.PersonMapper;
import br.com.invillia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public long insert(PersonRequest personRequest) {

        Person person;

        if(personRepository.findByCPF(personRequest.getCPF()) == null){
            person = personMapper.personRequestToPerson(personRequest);
            personRepository.save(person);
        }
        else{
            throw new CpfAlreadyExistsException("CPF ready exists!");
        }

        return person.getId();
    }

    @Transactional
    public void update(PersonRequest personRequest, long id) {

        Person person = personRepository.findById(id).orElseThrow( () -> new PersonNotFoundException("Person hadn't found!"));

        personMapper.updatePersonByPersonRequest(person, personRequest);

        personRepository.save(person);
    }

    @Transactional
    public void delete(long id) {

        Person person = personRepository.findById(id).orElseThrow( () -> new PersonNotFoundException("Person hadn't found!"));

        personRepository.delete(person);
    }

    @Transactional(readOnly = true)
    public Person findById(long id) {
        return personRepository.findById(id).orElseThrow( () -> new PersonNotFoundException("Person hadn't found!"));
    }
}
