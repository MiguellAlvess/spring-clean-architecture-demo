package br.com.miguelalves.spring_clean_architecture_demo.infra.persistence;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    
}
