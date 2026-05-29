package br.com.miguelalves.spring_clean_architecture_demo.integration.person;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import br.com.miguelalves.spring_clean_architecture_demo.integration.config.IntegrationTestBase;
import br.com.miguelalves.spring_clean_architecture_demo.utils.TestPersonFactory;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

class CreatePersonIT extends IntegrationTestBase {

        @Test
        void shouldCreatePersonSuccessfully() {
                Map<String, Object> requestBody = TestPersonFactory.createPersonWithoutAddressesRequest(
                                "Miguel Alves",
                                LocalDate.now().minusYears(30),
                                "12345678900");
                Response response = given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().log().ifValidationFails()
                                .statusCode(201)
                                .body("id", notNullValue())
                                .body("name", equalTo("Miguel Alves"))
                                .body("cpf", equalTo("12345678900"))
                                .body("age", equalTo(30))
                                .extract().response();
                Long createdId = response.jsonPath().getLong("id");
                assertThat(createdId).isNotNull();
                assertThat(personRepository.existsById(createdId)).isTrue();
        }

        @Test
        void shouldCreatePersonWithMultipleAddresses() {
                Map<String, Object> requestBody = TestPersonFactory.createPersonWithAddressesRequest(
                                "Daniela Silva",
                                LocalDate.now().minusYears(28),
                                "98765432100");
                given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().log().ifValidationFails()
                                .statusCode(201)
                                .body("id", notNullValue())
                                .body("name", equalTo("Daniela Silva"))
                                .body("cpf", equalTo("98765432100"))
                                .body("addresses", hasSize(2));
                assertThat(personRepository.count()).isEqualTo(1);
                assertThat(countAddresses()).isEqualTo(2);
        }

        @Test
        void shouldReturnConflictWhenCpfAlreadyExists() {
                Map<String, Object> requestBody = TestPersonFactory.createPersonWithAddressesRequest(
                                "Pedro Almeida",
                                LocalDate.now().minusYears(40),
                                "11122233344");
                given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().statusCode(201);
                given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().log().ifValidationFails()
                                .statusCode(409)
                                .body("error", containsString("CPF already exists"));
        }

        @Test
        void shouldReturnBadRequestWhenNameIsBlank() {
                Map<String, Object> requestBody = TestPersonFactory.createPersonWithoutAddressesRequest(
                                "",
                                LocalDate.now().minusYears(22),
                                "99900011122");

                given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().log().ifValidationFails()
                                .statusCode(400)
                                .body("error", equalTo("Validation failed"))
                                .body("details", hasItem(containsString("name: must not be blank")));
        }

        @Test
        void shouldReturnBadRequestWhenCpfIsBlank() {
                Map<String, Object> requestBody = TestPersonFactory.createPersonWithoutAddressesRequest(
                                "Gabriela Silva",
                                LocalDate.now().minusYears(27),
                                "");

                given()
                                .body(requestBody)
                                .when()
                                .post(PERSON_API)
                                .then().log().ifValidationFails()
                                .statusCode(400)
                                .body("error", equalTo("Validation failed"))
                                .body("details", hasItem(containsString("cpf: must not be blank")));
        }
}
