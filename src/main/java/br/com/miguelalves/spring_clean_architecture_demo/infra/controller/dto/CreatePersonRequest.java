package br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePersonRequest(
                @NotBlank String name,
                @NotNull LocalDate birthDate,
                @NotBlank String cpf,
                @NotNull(message = "addresses must not be null") @Size(min = 1, message = "addresses must have at least one address") @Valid List<AddressRequest> addresses) {
}
