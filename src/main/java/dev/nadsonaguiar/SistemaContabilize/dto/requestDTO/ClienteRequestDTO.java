package dev.nadsonaguiar.SistemaContabilize.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

}
