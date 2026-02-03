package dev.nadsonaguiar.SistemaContabilize.dto.responseDTO;

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
public class ProcessoResponseDTO {

    private Long id;
    private TipoProcesso tipoProcesso;
    private Long clienteId;
    private String clienteNome;
    private LocalDateTime dataCriacao;
    private String criadoPorNome;
}
