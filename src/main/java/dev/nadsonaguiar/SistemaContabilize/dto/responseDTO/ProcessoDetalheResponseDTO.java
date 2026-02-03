package dev.nadsonaguiar.SistemaContabilize.dto.responseDTO;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoProcesso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDetalheResponseDTO {
    private Long id;
    private TipoProcesso tipoProcesso;
    private LocalDateTime dataCriacao;
    // Dados do cliente
    private Long clienteId;
    private String clienteNome;
    // Dados de quem criou
    private String criadoPorNome;
    // Histórico
    private List<MovimentacaoResponseDTO> movimentacoes;
    // Tarefas ativas
    private List<TarefaResponseDTO> tarefasAtivas;
}
