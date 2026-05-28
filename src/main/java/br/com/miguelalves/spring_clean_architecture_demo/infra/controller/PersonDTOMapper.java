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
                request.getName(),
                request.getBirthDate(),
                request.getCpf(),
                request.getAddresses());
    }

    public Person toDomain(UpdatePersonRequest request, Long personId) {
        if (request == null) {
            return null;
        }
        return buildPerson(
                personId,
                request.getName(),
                request.getBirthDate(),
                request.getCpf(),
                request.getAddresses());
    }

    public PersonResponse toResponse(Person person) {
        if (person == null) {
            return null;
        }
        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setName(person.getName());
        response.setBirthDate(person.getBirthDate());
        response.setCpf(person.getCpf());
        response.setAge(person.getAge());

        List<AddressResponse> addresses = person.getAddresses()
                .stream()
                .map(this::toAddressResponse)
                .toList();

        response.setAddresses(addresses);
        return response;
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
        address.setId(request.getId());
        address.setStreet(request.getStreet());
        address.setNumber(request.getNumber());
        address.setNeighborhood(request.getNeighborhood());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCep(request.getCep());
        return address;
    }

    private AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setNumber(address.getNumber());
        response.setNeighborhood(address.getNeighborhood());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setCep(address.getCep());
        return response;
    }
}