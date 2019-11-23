package br.com.fundatec.carro.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarroService {

    public List<String> listarCarros () {
        List<String> carros = Arrays.asList("Celta", "Fusca", "Ferrari", "Uno");
        return carros;
    }
}
