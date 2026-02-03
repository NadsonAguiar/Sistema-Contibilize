package dev.nadsonaguiar.SistemaContabilize.dto.requestDTO;

import dev.nadsonaguiar.SistemaContabilize.Enum.TipoProcesso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoRequestDTO {

    private Long clienteId;
    private TipoProcesso tipoProcesso;

}
