package br.com.fundatec.carro.service;

import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.model.Reserva;
import br.com.fundatec.carro.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CarroService carroService;

    public ReservaService(ReservaRepository reservaRepository, CarroService carroService) {
        this.reservaRepository = reservaRepository;
        this.carroService = carroService;
    }

    public Reserva incluir (Reserva reserva, Long idCarro) {
        Carro carro = carroService.filtrarPorId(idCarro);
        reserva.setCarro(carro);
        return reservaRepository.save(reserva);
    }
}
