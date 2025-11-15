package br.com.miguelalves.spring_clean_architecture_demo.infra.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.CreateAccountInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Account;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final CreateAccountInteractor createAccountInteractor;
    private final AccountDTOMapper accountDTOMapper;
    
    public AccountController(CreateAccountInteractor createAccountInteractor, AccountDTOMapper accountDTOMapper) {
        this.createAccountInteractor = createAccountInteractor;
        this.accountDTOMapper = accountDTOMapper;
    }

    @PostMapping
    CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account accountBusinessObject = accountDTOMapper.toAccount(request);
        Account account =createAccountInteractor.createAccount(accountBusinessObject);
        return accountDTOMapper.toResponse(account);
    }
    
}
