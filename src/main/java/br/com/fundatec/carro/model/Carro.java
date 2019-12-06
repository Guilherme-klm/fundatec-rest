package br.com.fundatec.carro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-imcremento
    private Long id;
    private String nome;
    private String placa;
    private LocalDate dataFabricacao;
    private LocalDate dataModelo;
    private String marca;

    public Carro() {
    }

    public Carro(Long id, String nome, String placa) {
        this.id = id;
        this.nome = nome;
        this.placa = placa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataModelo() {
        return dataModelo;
    }

    public void setDataModelo(LocalDate dataModelo) {
        this.dataModelo = dataModelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
