package br.com.miguelalves.spring_clean_architecture_demo.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.AccountGateway;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.CreateAccountInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.AccountDTOMapper;
import br.com.miguelalves.spring_clean_architecture_demo.infra.gateways.AccountEntityMapper;
import br.com.miguelalves.spring_clean_architecture_demo.infra.gateways.AccountRepositoryGateway;
import br.com.miguelalves.spring_clean_architecture_demo.infra.persistence.AccountRepository;

@Configuration
public class AccountConfig {
    @Bean
    CreateAccountInteractor createAccountUseCase(AccountGateway accountGateway) {
        return new CreateAccountInteractor(accountGateway);
    }

    @Bean 
    AccountGateway accountGateway(AccountRepository accountRepository, AccountEntityMapper accountEntityMapper) {
        return new AccountRepositoryGateway(accountRepository, accountEntityMapper);
    }

    @Bean
    AccountEntityMapper accountEntityMapper() {
        return new AccountEntityMapper();
    }

    @Bean
    AccountDTOMapper accountDTOMapper() {
        return new AccountDTOMapper();
    }
}
