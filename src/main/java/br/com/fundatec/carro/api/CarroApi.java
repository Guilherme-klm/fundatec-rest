package br.com.fundatec.carro.api;

import br.com.fundatec.carro.mapper.CarroMapper;
import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.service.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarroApi {

    private final CarroService carroService;
    private final CarroMapper carroMapper;

    public CarroApi(CarroService carroService, CarroMapper carroMapper) {
        this.carroService = carroService;
        this.carroMapper = carroMapper;
    }

    @GetMapping("/carros") // retorna dados
    public ResponseEntity<List<CarroOutputDTO>> getCarros(@RequestParam(required = false, defaultValue = "") String nome) {

        List<Carro> carros = carroService.filtrarLista(nome);
        List<CarroOutputDTO> carroOutputDTOList = carroMapper.mapear(carros);

        if (carros.size() == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(carroOutputDTOList);
        }
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<CarroOutputDTO> getCarro (@PathVariable Long id) {
       Carro carro = carroService.filtrarPorId(id);

       if (carro == null) {
           return ResponseEntity.noContent().build();
       } else {
           CarroOutputDTO carroOutputDTO = carroMapper.mapear(carro);
           return ResponseEntity.ok(carroOutputDTO);
       }
    }
}



