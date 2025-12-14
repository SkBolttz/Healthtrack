package br.com.HEALTHTRACK.API.HEALTHTRACK.Service;

import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco.CepDto;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco.EnderecoDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.DTO.Endereco.EnderecoResponseDTO;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Entity.Endereco;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Enum.EnderecoEnum.UF;
import br.com.HEALTHTRACK.API.HEALTHTRACK.Repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI.*;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class EnderecoService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO salvaEndereco(EnderecoResponseDTO enderecoResponseDTO) throws IOException, InterruptedException {

        if (enderecoRepository.existsByCepAndNumero(enderecoResponseDTO.cep()
                , enderecoResponseDTO.numero())) {

            log.warn("Tentativa de criar endereço duplicado: CEP={} Número={}",
                    enderecoResponseDTO.cep(), enderecoResponseDTO.numero());

            throw new IllegalArgumentException("Endereço não pode ser criado pois já registrado");

        }
        try {
            log.info("Carregando endereço... ");
            CepDto viaCep = pegaCep(enderecoResponseDTO.cep());

            if (viaCep == null || viaCep.isErro()) {
                throw new IllegalArgumentException("CEP inválido ou inexistente");
            }

            Endereco novoEndereco = new Endereco();

            novoEndereco.setLogradouro(validaCampoCep(enderecoResponseDTO.logradouro(), viaCep.getLogradouro()));
            novoEndereco.setBairro(validaCampoCep(enderecoResponseDTO.bairro(), viaCep.getBairro()));
            novoEndereco.setCidade(validaCampoCep(enderecoResponseDTO.cidade(), viaCep.getCidade()));
            novoEndereco.setEstado((validaCampoUF(enderecoResponseDTO.estado(), viaCep.getEstado())));
            novoEndereco.setComplemento(validaCampoCep(enderecoResponseDTO.complemento(), viaCep.getComplemento()));
            novoEndereco.setNumero(enderecoResponseDTO.numero());

            log.info("Carregado com sucesso!");


            Endereco endereco = enderecoRepository.save(novoEndereco);
            log.info("Endereço salvo! logradouro={} bairro={} cidade={} estado={} cep={} numero={} complemento={}",
                    novoEndereco.getLogradouro(),
                    novoEndereco.getBairro(),
                    novoEndereco.getCidade(),
                    novoEndereco.getEstado(),
                    novoEndereco.getCep(),
                    novoEndereco.getNumero(),
                    novoEndereco.getComplemento());

            return new EnderecoDTO(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep(),
                    endereco.getComplemento(),
                    endereco.getNumero()
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CepDto pegaCep(String cep) throws IOException, InterruptedException {
        CepDto cepDto = new CepDto();

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            cepDto = mapper.readValue(response.body(), CepDto.class);

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace().getMessage());
        }
        return cepDto;
    }


    // Este metodo valida caso o campo do usuario for nulo e tiver o CEP ele ira pegar o valor do viaCep

    //Metodo abaixo mesma coisa porém retorna um ENUM

    private String validaCampoCep(String valorUsuario, String valorViaCep) {
        return (valorUsuario == null || valorUsuario.isBlank()) ? valorViaCep : valorUsuario;
    }

    private UF validaCampoUF(UF valorUsuario, UF valorViaCep) {
        if (valorUsuario != null) {
            return valorUsuario;
        }
        if (valorViaCep != null) {
            return valorViaCep;
        }
        return null;
    }

}
