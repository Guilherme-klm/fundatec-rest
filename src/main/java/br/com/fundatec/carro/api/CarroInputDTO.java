package br.com.fundatec.carro.api;

import org.apache.tomcat.jni.Local;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CarroInputDTO {

    @NotBlank (message = "O campo nome é obrigatório!") // Valida que não pode ser nem vazio e branco
    private String nome;

    // @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$") validar cpf

    @Pattern(regexp = "^[A-Z]{3}[0-9]{4}$", message = "Placa inválida")
    @NotBlank (message = "O campo placa é obrigatório!")
    private String placa;

    @NotNull(message = "Não pode ser nulo")
    @Past(message = "A data não pode estar no futuro")
    private LocalDate dataFabricacao;

    @NotNull(message = "Não pode ser nulo")
    private LocalDate dataModelo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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
}
