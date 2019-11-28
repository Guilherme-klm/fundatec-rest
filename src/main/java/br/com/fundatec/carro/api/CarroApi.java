package br.com.fundatec.carro.api;

import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarroApi {

    private final CarroService carroService;

    public CarroApi(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping("/carros") // retorna dados
    public ResponseEntity<List<Carro>> getCarros(@RequestParam(required = false, defaultValue = "") String nome) {

        List<Carro> carros = carroService.filtrarLista(nome);

        if (carros.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carros);
        } else {
            return ResponseEntity.ok(carros);
        }
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<Carro> getCarro (@PathVariable Long id) {
       Carro carro = carroService.filtrarPorId(id);
       if (carro == null) {
           return  ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.ok(carro);
       }
    }
}



