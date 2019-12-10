package br.com.fundatec.carro.mapper;

import br.com.fundatec.carro.api.dto.ReservaInputDTO;
import br.com.fundatec.carro.api.dto.ReservaOutputDTO;
import br.com.fundatec.carro.model.Reserva;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservaMapper {

    private final CarroMapper carroMapper;

    public ReservaMapper(CarroMapper carroMapper) {
        this.carroMapper = carroMapper;
    }

    public Reserva mapear (ReservaInputDTO reservaInputDTO) {
        Reserva reserva = new Reserva();
        reserva.setId(reservaInputDTO.getId());
        reserva.setNome(reservaInputDTO.getNome());
        reserva.setDataInicio(reservaInputDTO.getDataInicio());
        reserva.setDataFim(reservaInputDTO.getDataFim());

        return reserva;
    }

    public ReservaOutputDTO mapear (Reserva reserva) {
        ReservaOutputDTO reservaOutputDTO = new ReservaOutputDTO();
        reservaOutputDTO.setId(reserva.getId());
        reservaOutputDTO.setNome(reserva.getNome());
        reservaOutputDTO.setDataInicio(reserva.getDataInicio());
        reservaOutputDTO.setDataFim(reserva.getDataFim());
        reservaOutputDTO.setCarroOutputDTO(carroMapper.mapear(reserva.getCarro()));
        return reservaOutputDTO;
    }

    public List<ReservaOutputDTO> mapear (List<Reserva> reservas) {
        List<ReservaOutputDTO> reservaOutputDTOList = new ArrayList<>();
        for (Reserva reserva: reservas) {
            reservaOutputDTOList.add(mapear(reserva));
        }
        return reservaOutputDTOList;
    }
}
