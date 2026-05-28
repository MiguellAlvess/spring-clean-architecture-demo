package br.com.miguelalves.spring_clean_architecture_demo.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.PersonGateway;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.CreatePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.DeletePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.FindAllPersonsInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.FindPersonByIdInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.UpdatePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.PersonDTOMapper;
import br.com.miguelalves.spring_clean_architecture_demo.infra.gateways.PersonEntityMapper;
import br.com.miguelalves.spring_clean_architecture_demo.infra.gateways.PersonRepositoryGateway;
import br.com.miguelalves.spring_clean_architecture_demo.infra.persistence.PersonRepository;

@Configuration
public class PersonConfig {

    @Bean
    CreatePersonInteractor createPersonInteractor(PersonGateway gateway) {
        return new CreatePersonInteractor(gateway);
    }

    @Bean
    FindAllPersonsInteractor findAllPersonsInteractor(PersonGateway gateway) {
        return new FindAllPersonsInteractor(gateway);
    }

    @Bean
    FindPersonByIdInteractor findPersonByIdInteractor(PersonGateway gateway) {
        return new FindPersonByIdInteractor(gateway);
    }

    @Bean
    UpdatePersonInteractor updatePersonInteractor(PersonGateway gateway) {
        return new UpdatePersonInteractor(gateway);
    }

    @Bean
    DeletePersonInteractor deletePersonInteractor(PersonGateway gateway) {
        return new DeletePersonInteractor(gateway);
    }

    @Bean
    PersonGateway personGateway(PersonRepository repo, PersonEntityMapper mapper) {
        return new PersonRepositoryGateway(repo, mapper);
    }

    @Bean
    PersonEntityMapper personEntityMapper() {
        return new PersonEntityMapper();
    }

    @Bean
    PersonDTOMapper personDTOMapper() {
        return new PersonDTOMapper();
    }
}
