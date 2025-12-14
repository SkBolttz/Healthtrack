package br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco;

import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.EnderecoEnum.UF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CepDto  {
    private String logradouro;
    private String bairro;
    private String cidade;
    private UF estado;
    private String complemento;
    private boolean erro;


}
