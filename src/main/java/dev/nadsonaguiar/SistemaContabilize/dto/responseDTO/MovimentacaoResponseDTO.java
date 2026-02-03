package dev.nadsonaguiar.SistemaContabilize.dto.responseDTO;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoResponseDTO {
    private Long id;
    private TipoMovimentacao tipo;
    private String descricao;
    private LocalDateTime data;
    private String usuarioNome; // Quem fez a movimentação
    private Long tarefaId;
    private String tarefaNome;
}
