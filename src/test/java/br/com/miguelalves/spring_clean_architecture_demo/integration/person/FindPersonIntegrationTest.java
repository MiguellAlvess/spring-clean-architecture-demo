package br.com.miguelalves.spring_clean_architecture_demo.integration.person;

import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;

import br.com.miguelalves.spring_clean_architecture_demo.integration.config.IntegrationTestBase;
import br.com.miguelalves.spring_clean_architecture_demo.utils.TestPersonFactory;
import static io.restassured.RestAssured.given;

class FindPersonIntegrationTest extends IntegrationTestBase {

        @Test
        void shouldFindPersonById() {
                Map<String, Object> requestBody = TestPersonFactory.createValidPersonRequest(
                                "Felipe Santos",
                                LocalDate.now().minusYears(25),
                                "22233344455");
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
                                .get(PERSON_API + "/{id}")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(200)
                                .body("id", equalTo(id.intValue()))
                                .body("name", equalTo("Felipe Santos"))
                                .body("cpf", equalTo("22233344455"))
                                .body("age", equalTo(25))
                                .body("addresses", hasSize(2));
        }

        @Test
        void shouldReturnNotFoundWhenPersonDoesNotExist() {
                given()
                                .pathParam("id", 9999)
                                .when()
                                .get(PERSON_API + "/{id}")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(404);
        }
}