package br.com.invillia.repository;

import br.com.invillia.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByCPF(String cpf);
}

