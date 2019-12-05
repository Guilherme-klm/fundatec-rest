package br.com.fundatec.carro.service;

import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Carro incluir(Carro carro) {
        validar(carro);
        validarMarca(carro);
       return carroRepository.incluir(carro);
    }

    public void validar (Carro carro) {
        if (carro.getDataModelo().isBefore(carro.getDataFabricacao())) {
             throw new RuntimeException("Data modelo é inválida");
        }
    }

    public void validarMarca (Carro carro) {
        List<String> listaMarcas = Arrays.asList("Peugeot", "Renault", "Fiat");

        if (!(listaMarcas.contains(carro.getMarca()))) {
            throw new RuntimeException("A marca " + carro.getMarca() + " é inválida");
        }
    }
}
