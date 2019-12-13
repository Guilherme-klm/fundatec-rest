package br.com.fundatec.carro.api;

import br.com.fundatec.carro.api.dto.CarroInputDTO;
import br.com.fundatec.carro.api.dto.CarroOutputDTO;
import br.com.fundatec.carro.api.dto.ErroDTO;
import br.com.fundatec.carro.mapper.CarroMapper;
import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.service.CarroService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
        return getListResponseEntity(carros);
    }

    @GetMapping("/carros/datas") // retorna dados
    public ResponseEntity<List<CarroOutputDTO>> getCarrosEntreDatas (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        List<Carro> carros = carroService.filtrarPorData(dataInicio, dataFim);
        return getListResponseEntity(carros);
    }

    private ResponseEntity<List<CarroOutputDTO>> getListResponseEntity(List<Carro> carros) {
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

    @PostMapping("/carros")
    public ResponseEntity<?> incluir (@Valid @RequestBody CarroInputDTO carroInputDTO) {
        Carro carro = carroMapper.mapear(carroInputDTO); //@Valid valida o @NotBlank de uma classe
        try {
            carro = carroService.incluir(carro);
            CarroOutputDTO carroOutputDTO = carroMapper.mapear(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroOutputDTO);
        } catch (RuntimeException e) {
            ErroDTO erroDTO = new ErroDTO();
            erroDTO.setMensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erroDTO);
        }
    }

    @PutMapping("/carros/{id}")
    public ResponseEntity<?> atualizarCarro (@PathVariable Long id, @Valid @RequestBody CarroInputDTO carroInputDTO) {
        Carro carro = carroMapper.mapear(carroInputDTO);
        carro = carroService.atualizar(id, carro);
        if (carro == null) {
            return ResponseEntity.noContent().build();
        } else {
            CarroOutputDTO carroOutputDTO = carroMapper.mapear(carro);
            return ResponseEntity.ok().body(carroOutputDTO);
        }
    }
}



