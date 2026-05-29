package br.com.miguelalves.spring_clean_architecture_demo.integration.person;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import br.com.miguelalves.spring_clean_architecture_demo.integration.config.IntegrationTestBase;
import br.com.miguelalves.spring_clean_architecture_demo.utils.TestPersonFactory;
import static io.restassured.RestAssured.given;

class DeletePersonIntegrationTest extends IntegrationTestBase {

        @Test
        void shouldDeletePersonSuccessfully() {
                Map<String, Object> requestBody = TestPersonFactory.createValidPersonRequest(
                                "Carlos Duarte",
                                LocalDate.now().minusYears(45),
                                "77788899900");
                Long id = given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then()
                                .statusCode(201)
                                .extract()
                                .jsonPath()
                                .getLong("id");
                given()
                                .pathParam("id", id)
                                .when()
                                .delete(PERSON_API + "/{id}")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(204);
                assertThat(personRepository.findById(id)).isEmpty();
                assertThat(countAddresses()).isEqualTo(0);
        }

        @Test
        void shouldReturnNotFoundWhenDeletingNonExistentPerson() {
                given()
                                .pathParam("id", 9999)
                                .when()
                                .delete(PERSON_API + "/{id}")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(404)
                                .body("error", equalTo("Person not found"));
        }
}