package br.com.fundatec.carro.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CarroRepository {

    public List<String> listarCarros () {
        List<String> carros = Arrays.asList("Celta",
                "Fusca",
                "Ferrari",
                "Uno",
                "Camaro",
                "Maverick",
                "Triciclo"
        );
        return carros;
    }

    public List<String> filtrarLista (String nome) {
        List<String> palavrasFiltradas =  new ArrayList<>();

        for (String carro : listarCarros()) {
            if (carro.toLowerCase().contains(nome.toLowerCase())) {
                palavrasFiltradas.add(carro);
            }
        }
        return palavrasFiltradas;
    }
}
