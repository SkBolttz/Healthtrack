package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper.Tratamento;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Tratamento.TratamentoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.DoencaPaciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Tratamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TratamentoMapper {

    List<Tratamento>toEntityList(List<TratamentoDTO> tratamentos);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "dataInicio", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "doenca", ignore = true)
    @Mapping(target = "profissionalSaude", ignore = true)
    @Mapping(target = "medicacoes", ignore = true)
    Tratamento toEntity (TratamentoDTO tratamentoDTO, Paciente paciente, DoencaPaciente dp);
}
