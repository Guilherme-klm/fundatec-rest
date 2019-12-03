package br.com.fundatec.carro.mapper;

import br.com.fundatec.carro.api.CarroInputDTO;
import br.com.fundatec.carro.api.CarroOutputDTO;
import br.com.fundatec.carro.model.Carro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarroMapper {

    public CarroOutputDTO mapear (Carro carro) {
        CarroOutputDTO carroOutputDTO = new CarroOutputDTO();
        carroOutputDTO.setId(carro.getId());
        carroOutputDTO.setNome(carro.getNome());
        carroOutputDTO.setPlaca(carro.getPlaca());
        carroOutputDTO.setDataFabricacao(carro.getDataFabricacao());
        carroOutputDTO.setDataModelo(carro.getDataModelo());

        return carroOutputDTO;
    }

    public List<CarroOutputDTO> mapear (List<Carro> carros) {
        List<CarroOutputDTO> carroOutputDTOList = new ArrayList();
        for (Carro carro: carros) {
            carroOutputDTOList.add(mapear(carro));
        }
        return carroOutputDTOList;
    }

    public Carro mapear (CarroInputDTO carroInputDTO) {
        Carro carro = new Carro();
        carro.setNome(carroInputDTO.getNome());
        carro.setPlaca(carroInputDTO.getPlaca());
        carro.setDataFabricacao(carroInputDTO.getDataFabricacao());
        carro.setDataModelo(carroInputDTO.getDataModelo());
        return carro;
    }

}
