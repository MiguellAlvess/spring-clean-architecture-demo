package br.com.miguelalves.spring_clean_architecture_demo.infra.controller;

import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;

public class AccountDTOMapper {
    CreateAccountResponse toResponse(Account account) {
        return new CreateAccountResponse(account.name(), account.email());
    }

    public Account toAccount(CreateAccountRequest request) {
        return new Account(request.name(), request.password(), request.email());
    }
}
