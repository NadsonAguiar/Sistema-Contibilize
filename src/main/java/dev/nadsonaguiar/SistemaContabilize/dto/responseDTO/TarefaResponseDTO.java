package dev.nadsonaguiar.SistemaContabilize.dto.responseDTO;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoProcesso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {

    private Long id;
    private String atividadeNome;
    private Long processoId;
    private TipoProcesso processoTipo;
    private Long responsavelId;
    private String responsavelNome;
    private StatusTarefa status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevista;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    private String descricao;


}
