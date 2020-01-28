package br.com.invillia.mapper;

import br.com.invillia.domain.Person;
import br.com.invillia.domain.request.PersonRequest;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person personRequestToPerson(PersonRequest personRequest){

        Person person = new Person();
        
        person.setName(personRequest.getName());
        person.setCPF(personRequest.getCPF());

        return person;
    }

    public void updatePersonByPersonRequest(Person person, PersonRequest personRequest) {

        person.setName(personRequest.getName());
        person.setCPF(personRequest.getCPF());
    }
}
