package br.com.miguelalves.spring_clean_architecture_demo.infra.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Address;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Person;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.AddressRequest;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.AddressResponse;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.CreatePersonRequest;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.PersonResponse;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.UpdatePersonRequest;

@Component
public class PersonDTOMapper {

    public Person toDomain(CreatePersonRequest request) {
        if (request == null) {
            return null;
        }
        return buildPerson(
                null,
                request.name(),
                request.birthDate(),
                request.cpf(),
                request.addresses());
    }

    public Person toDomain(UpdatePersonRequest request, Long personId) {
        if (request == null) {
            return null;
        }
        return buildPerson(
                personId,
                request.name(),
                request.birthDate(),
                request.cpf(),
                request.addresses());
    }

    public PersonResponse toResponse(Person person) {
        if (person == null) {
            return null;
        }
        List<AddressResponse> addresses = person.getAddresses()
                .stream()
                .map(this::toAddressResponse)
                .toList();

        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getBirthDate(),
                person.getCpf(),
                person.getAge(),
                addresses);
    }

    private Person buildPerson(
            Long id,
            String name,
            LocalDate birthDate,
            String cpf,
            List<AddressRequest> addressRequests) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setBirthDate(birthDate);
        person.setCpf(cpf);
        List<Address> addresses = mapAddresses(addressRequests);
        person.setAddresses(addresses);
        return person;
    }

    private List<Address> mapAddresses(List<AddressRequest> addressRequests) {
        if (Objects.isNull(addressRequests)) {
            return List.of();
        }
        return addressRequests.stream()
                .map(this::toAddressDomain)
                .toList();
    }

    private Address toAddressDomain(AddressRequest request) {
        Address address = new Address();
        address.setId(request.id());
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setNeighborhood(request.neighborhood());
        address.setCity(request.city());
        address.setState(request.state());
        address.setCep(request.cep());
        return address;
    }

    private AddressResponse toAddressResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCep());
    }
}