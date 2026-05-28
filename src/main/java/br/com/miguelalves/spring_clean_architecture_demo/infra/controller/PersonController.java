package br.com.miguelalves.spring_clean_architecture_demo.infra.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.CreatePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.DeletePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.FindAllPersonsInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.FindPersonByIdInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.application.usecases.UpdatePersonInteractor;
import br.com.miguelalves.spring_clean_architecture_demo.domain.entity.Person;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.CreatePersonRequest;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.PersonResponse;
import br.com.miguelalves.spring_clean_architecture_demo.infra.controller.dto.UpdatePersonRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final CreatePersonInteractor createPersonInteractor;
    private final FindAllPersonsInteractor findAllPersonsInteractor;
    private final FindPersonByIdInteractor findPersonByIdInteractor;
    private final UpdatePersonInteractor updatePersonInteractor;
    private final DeletePersonInteractor deletePersonInteractor;
    private final PersonDTOMapper dtoMapper;

    public PersonController(CreatePersonInteractor createPersonInteractor,
            FindAllPersonsInteractor findAllPersonsInteractor,
            FindPersonByIdInteractor findPersonByIdInteractor, UpdatePersonInteractor updatePersonInteractor,
            DeletePersonInteractor deletePersonInteractor, PersonDTOMapper dtoMapper) {
        this.createPersonInteractor = createPersonInteractor;
        this.findAllPersonsInteractor = findAllPersonsInteractor;
        this.findPersonByIdInteractor = findPersonByIdInteractor;
        this.updatePersonInteractor = updatePersonInteractor;
        this.deletePersonInteractor = deletePersonInteractor;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody CreatePersonRequest req) {
        Person domain = dtoMapper.toDomain(req);
        Person created = createPersonInteractor.create(domain);
        PersonResponse resp = dtoMapper.toResponse(created);
        return ResponseEntity.created(URI.create("/api/persons/" + created.getId())).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll() {
        var list = findAllPersonsInteractor.findAll().stream().map(dtoMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable Long id) {
        return findPersonByIdInteractor.findById(id).map(dtoMapper::toResponse).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @Valid @RequestBody UpdatePersonRequest req) {
        Person domain = dtoMapper.toDomain(req, id);
        Person updated = updatePersonInteractor.update(domain);
        return ResponseEntity.ok(dtoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePersonInteractor.delete(id);
        return ResponseEntity.noContent().build();
    }
}
