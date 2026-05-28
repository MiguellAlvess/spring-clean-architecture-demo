package br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto;

public record AddressResponse(
        Long id,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String cep) {
}
