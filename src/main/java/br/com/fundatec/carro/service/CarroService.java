package br.com.fundatec.carro.service;

import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> filtrarLista (String nome) {
        return carroRepository.filtrarLista(nome);
    }

    public Carro filtrarPorId (Long id) {
        return carroRepository.filtrarPorId(id);
    }
}
