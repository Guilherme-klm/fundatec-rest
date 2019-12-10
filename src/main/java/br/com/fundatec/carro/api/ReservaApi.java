package br.com.fundatec.carro.api;

import br.com.fundatec.carro.api.dto.ReservaInputDTO;
import br.com.fundatec.carro.api.dto.ReservaOutputDTO;
import br.com.fundatec.carro.mapper.ReservaMapper;
import br.com.fundatec.carro.model.Reserva;
import br.com.fundatec.carro.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ReservaApi {

    private final ReservaMapper reservaMapper;
    private final ReservaService reservaService;

    public ReservaApi(ReservaMapper reservaMapper, ReservaService reservaService) {
        this.reservaMapper = reservaMapper;
        this.reservaService = reservaService;
    }

    @PostMapping("/carros/{id}/reservas")
    public ResponseEntity<?> incluir (@Valid @RequestBody ReservaInputDTO reservaInputDTO,
                                      @PathVariable(value = "id") Long idCarro) {
            Reserva reserva = reservaMapper.mapear(reservaInputDTO);
            reservaService.incluir(reserva,idCarro);
            ReservaOutputDTO reservaOutputDTO = reservaMapper.mapear(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservaOutputDTO);
    }
}
