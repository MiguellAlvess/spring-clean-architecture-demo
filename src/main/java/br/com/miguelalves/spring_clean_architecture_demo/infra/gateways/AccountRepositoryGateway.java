package br.com.miguelalves.spring_clean_architecture_demo.infra.gateways;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.AccountGateway;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;
import br.com.miguelalves.spring_clean_architecture_demo.infra.persistence.AccountEntity;
import br.com.miguelalves.spring_clean_architecture_demo.infra.persistence.AccountRepository;

public class AccountRepositoryGateway implements AccountGateway{

    private final AccountRepository accountRepository;
    private AccountEntityMapper accountEntityMapper;


    public AccountRepositoryGateway(AccountRepository accountRepository, AccountEntityMapper accountEntityMapper) {
        this.accountRepository = accountRepository;
        this.accountEntityMapper = accountEntityMapper;
    }

    @Override
    public Account createAccount(Account accountDomainObject) {
        AccountEntity accountEntity = accountEntityMapper.toEntity(accountDomainObject);
        AccountEntity savedObj =  accountRepository.save(accountEntity);
        return accountEntityMapper.toDomainObject(savedObj);
    }
    
}
