package br.com.fundatec.carro.integration;

import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.repository.CarroRepository;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcluirCarroTest {

    @LocalServerPort
    private int randomPort;

    @Autowired // instancia quando sobe a aplicacao
    private CarroRepository carroRepository;

    private Carro carro;

    @Before // inicia antes de todos os testes
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost"; // RestAssured - ferramente para teste de integracao
        RestAssured.port = randomPort;

        carroRepository.deleteAll();

        carro = new Carro();
        carro.setNome("mustang");
        carro.setPlaca("AJO3142");
        carro.setMarca("Fiat");
        carro.setDataModelo(LocalDate.of(2000, 10, 3));
        carro.setDataFabricacao(LocalDate.of(1999,12,3));

        carro = carroRepository.save(carro);
    }

    @Test
    public void deveExcluirUmCarro () {
        RestAssured.given()
                .when()
                .delete("/carros/{id}", carro.getId())
                .then()
                .statusCode(HttpStatus.OK.value());

        Assert.assertEquals(0, carroRepository.count());
    }
}
