package br.com.invillia.controller;

import br.com.invillia.domain.Person;
import br.com.invillia.domain.request.PersonRequest;
import br.com.invillia.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable long id){
        return personService.findById(id);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid PersonRequest personRequest){

        long id = personService.insert(personRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/person/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid PersonRequest personRequest, @PathVariable long id){

        personService.update(personRequest,id);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/person/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){

        personService.delete(id);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/person/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }
}