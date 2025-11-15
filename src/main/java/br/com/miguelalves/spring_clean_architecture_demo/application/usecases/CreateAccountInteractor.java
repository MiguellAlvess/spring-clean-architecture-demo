package br.com.miguelalves.spring_clean_architecture_demo.application.usecases;

import br.com.miguelalves.spring_clean_architecture_demo.application.gateways.AccountGateway;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;

public class CreateAccountInteractor {
    
    private AccountGateway accountGateway;

    public CreateAccountInteractor(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }
    
    public Account createAccount(Account account) {
        return accountGateway.createAccount(account);
    }
}
