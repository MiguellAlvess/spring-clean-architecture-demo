package br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        Long id,
        @NotBlank String street,
        String number,
        String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String cep) {
}
