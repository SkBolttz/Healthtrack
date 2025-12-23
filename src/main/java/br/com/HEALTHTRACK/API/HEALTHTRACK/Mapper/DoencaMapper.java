package br.com.HEALTHTRACK.API.HEALTHTRACK.Mapper;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.AtualizarDoencaDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaCadastroDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Doenca.DoencaDetalhesDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Doenca;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoencaMapper {

    Doenca converterDoencaEntidadeCadastro(DoencaCadastroDTO doencaCadastroDTO);
    DoencaDetalhesDTO toDetalhesDTO(Doenca doenca);
}
