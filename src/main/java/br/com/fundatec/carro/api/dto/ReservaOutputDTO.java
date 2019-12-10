package br.com.fundatec.carro.api.dto;

import java.time.LocalDate;

public class ReservaOutputDTO {

    private Long id;

    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private CarroOutputDTO carroOutputDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public CarroOutputDTO getCarroOutputDTO() {
        return carroOutputDTO;
    }

    public void setCarroOutputDTO(CarroOutputDTO carroOutputDTO) {
        this.carroOutputDTO = carroOutputDTO;
    }
}
