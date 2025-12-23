package br.com.HEALTHTRACK.API.HEALTHTRACK.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "doenca")
public class Doenca {

    // Esta entidade sera responsavel por armazenar informacoes sobre doencas
    // Podera ser reutilizada em varios paciente, posterior e necessario utilizar a entidade DoencaPaciente para vincular as doencas aos pacientes
    // Pedro Borba -- Since 20-DEZ-2025

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nomeDoenca;

    @NotBlank
    @Column(unique = true)
    private String codigoCid;

    @Column(length = 2000)
    @NotBlank
    private String descricao;

}

