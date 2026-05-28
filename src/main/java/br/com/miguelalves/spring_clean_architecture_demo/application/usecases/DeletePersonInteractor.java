package br.com.miguelalves.spring_clean_architecture_demo.application.usecases;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.PersonGateway;

public class DeletePersonInteractor {
    private final PersonGateway personGateway;

    public DeletePersonInteractor(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public void delete(Long id) {
        personGateway.delete(id);
    }
}
