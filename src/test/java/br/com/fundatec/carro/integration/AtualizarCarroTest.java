package br.com.fundatec.carro.integration;

import br.com.fundatec.carro.api.dto.CarroOutputDTO;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtualizarCarroTest {

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
    public void deveAtualizarUmCarro() {

    CarroOutputDTO carroOutputDTO = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\t\"nome\": \"Prisma\",\n" +
                        "\t\"dataFabricacao\": \"2019-01-01\",\n" +
                        "\t\"dataModelo\": \"2019-01-01\",\n" +
                        "\t\"placa\": \"OKS4912\",\n" +
                        "\t\"marca\": \"Fiat\"\n" +
                        "}")
                .when()
                .put("/carros/{id}", carro.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CarroOutputDTO.class);

        Assert.assertEquals("Prisma", carroOutputDTO.getNome());
        Assert.assertEquals("2019-01-01", carroOutputDTO.getDataFabricacao().toString());
        Assert.assertEquals("2019-01-01", carroOutputDTO.getDataModelo().toString());
        Assert.assertEquals("OKS4912", carroOutputDTO.getPlaca());
        Assert.assertEquals("Fiat", carroOutputDTO.getMarca());


    }

    @Test
    public void deveRetornarVazioQuandoAtualizarUmCarroInexistente() {
        RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "\t\"nome\": \"Prisma\",\n" +
                        "\t\"dataFabricacao\": \"2019-01-01\",\n" +
                        "\t\"dataModelo\": \"2019-01-01\",\n" +
                        "\t\"placa\": \"OKS4912\",\n" +
                        "\t\"marca\": \"Fiat\"\n" +
                        "}")
                .when()
                .put("/carros/{id}", -1)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
