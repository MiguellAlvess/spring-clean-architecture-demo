package br.com.miguelalves.spring_clean_architecture_demo.application.usecases;

import java.util.List;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.PersonGateway;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Person;

public class FindAllPersonsInteractor {
    private final PersonGateway personGateway;

    public FindAllPersonsInteractor(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public List<Person> findAll() {
        return personGateway.findAll();
    }
}
