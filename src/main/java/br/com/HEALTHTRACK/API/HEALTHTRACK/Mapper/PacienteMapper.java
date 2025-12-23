package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Paciente.*;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente convertePacienteEntidadeCadastro(PacienteCadastroDTO pacienteCadastroDTO);
    PacienteDetalhesDTO converterPacienteDtoDetalhes(Paciente paciente);
    PacienteResumoDTO converterPacienteResumoDto(Paciente paciente);
}
