package br.com.miguelalves.spring_clean_architecture_demo.infra.controller;

public record CreateAccountRequest(String name, String password, String email) {
    
}
