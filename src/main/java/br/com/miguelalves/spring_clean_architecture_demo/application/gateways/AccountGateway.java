package br.com.miguelalves.spring_clean_architecture_demo.application.gateways;

import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;

public interface AccountGateway {
    Account createAccount(Account account);
}
