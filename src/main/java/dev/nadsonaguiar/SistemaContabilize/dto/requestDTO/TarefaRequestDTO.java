package dev.nadsonaguiar.SistemaContabilize.dto.requestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de uma tarefa")
public class TarefaRequestDTO {

    @Schema(description = "ID da atividade", example = "1", required = true)
    @NotNull(message = "Atividade é obrigatória")
    private Long atividadeId;

    @Schema(description = "ID do processo", example = "10", required = true)
    @NotNull(message = "Processo é obrigatório")
    private Long processoId;

    @Schema(description = "ID do usuário responsável", example = "2", required = true)
    @NotNull(message = "Responsável é obrigatório")
    private Long responsavelId;

    @Schema(description = "Data de início da tarefa", example = "2026-01-30T23:37:43.075Z")
    @NotNull(message = "Data de inicio é obrigatório")
    private LocalDateTime dataInicio;

    @Schema(description = "Data prevista de conclusão", example = "2026-02-10T15:34:43.075Z")
    private LocalDateTime dataPrevista;

    @Schema(description = "Descrição da tarefa")
    private String descricao;
}
