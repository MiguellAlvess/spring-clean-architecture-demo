package br.com.miguelalves.spring_clean_architecture_demo.application.usecases;

import java.util.Optional;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.PersonGateway;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Person;

public class FindPersonByIdInteractor {
    private final PersonGateway personGateway;

    public FindPersonByIdInteractor(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public Optional<Person> findById(Long id) {
        return personGateway.findById(id);
    }
}
