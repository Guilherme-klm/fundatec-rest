package br.com.fundatec.carro.repository;

import br.com.fundatec.carro.model.Carro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CarroRepository {

    private static List<Carro> listarCarros = Arrays.asList(
                new Carro(1L, "Mustang", "EVO6969"),
                new Carro(2L, "Uno", "VEI1031"),
                new Carro(3L, "Prisma", "VTC9931"));

    public List<Carro> filtrarLista(String nome) {
        List<Carro> palavrasFiltradas = new ArrayList<>();

        for (Carro carro : listarCarros) {
            if (carro.getNome().toLowerCase().contains(nome.toLowerCase())) {
                palavrasFiltradas.add(carro);
            }
        }
        return palavrasFiltradas;
    }

    public Carro filtrarPorId (Long id) {
        for (Carro carro : listarCarros) {
            if (carro.getId().equals(id)) {
                return carro;
            }
        }
        return null;
    }
}
