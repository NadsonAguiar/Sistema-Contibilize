package dev.nadsonaguiar.SistemaContabilize.dto;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoProcesso;
import dev.nadsonaguiar.SistemaContabilize.model.AtividadeModel;
import dev.nadsonaguiar.SistemaContabilize.model.ProcessoModel;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDashboardDTO {

    private Long tarefaId;
    private String tarefaNome;
    private Long processoId;
    private TipoProcesso tipoProcesso;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevista;
    private StatusTarefa statusAtual;
    private String descricao;

}
