package br.com.miguelalves.spring_clean_architecture_demo.infra.gateways;

import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;
import br.com.miguelalves.spring_clean_architecture_demo.infra.persistence.AccountEntity;

public class AccountEntityMapper {
    
    AccountEntity toEntity(Account accountDomainObject) {
        return new AccountEntity(accountDomainObject.name(), accountDomainObject.password(), 
        accountDomainObject.email());
    }

    Account toDomainObject(AccountEntity accountEntity) {
        return new Account(accountEntity.getName(), accountEntity.getPassword(), accountEntity.getEmail());
    }
}
