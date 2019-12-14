package br.com.fundatec.carro.service;

import br.com.fundatec.carro.api.dto.CarroInputDTO;
import br.com.fundatec.carro.model.Carro;
import br.com.fundatec.carro.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository; // 'final' precisa ser iniciado com um valor, ele não pode ser alterado

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> filtrarLista (String nome) {
        return carroRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Carro filtrarPorId (Long id) {
        return carroRepository.findById(id).orElse(null); // procurar por id e se n achar dar null
    }

    public List<Carro> filtrarPorData (LocalDate dataInicio, LocalDate dataFim) {
        return carroRepository.findByDataFabricacaoBetween(dataInicio, dataFim);
    }

    public Carro incluir(Carro carro) {
        validar(carro);
        validarMarca(carro);
       return carroRepository.save(carro);
    }

    public void validar (Carro carro) {
        if (carro.getDataModelo().isBefore(carro.getDataFabricacao())) {
             throw new RuntimeException("Data modelo é inválida");
        }
    }

    public void validarMarca (Carro carro) {
        List<String> listaMarcas = Arrays.asList("Peugeot", "Renault", "Fiat", "Tesla");

        if (!(listaMarcas.contains(carro.getMarca()))) {
            throw new RuntimeException("A marca " + carro.getMarca() + " é inválida");
        }
    }

    public Carro atualizar (Long id, Carro carroParaAtualizar) {
        Carro carro = filtrarPorId(id);
        if (carro != null) {
            carro.setNome(carroParaAtualizar.getNome());
            carro.setMarca(carroParaAtualizar.getMarca());
            carro.setPlaca(carroParaAtualizar.getPlaca());
            carro.setDataFabricacao(carroParaAtualizar.getDataFabricacao());
            carro.setDataModelo(carroParaAtualizar.getDataModelo());
            carro = carroRepository.save(carro);
        }
        return carro;
    }

    public void excluirPorId (Long id) {
        carroRepository.deleteById(id);
    }
}
