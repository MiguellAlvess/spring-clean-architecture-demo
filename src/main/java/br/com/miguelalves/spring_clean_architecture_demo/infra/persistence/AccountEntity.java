package br.com.miguelalves.spring_clean_architecture_demo.infra.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ACCOUNT")
public class AccountEntity {
    @Id
    private Long id;
    private String name;
    private String password;

    public AccountEntity(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    private String email;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
  
}
