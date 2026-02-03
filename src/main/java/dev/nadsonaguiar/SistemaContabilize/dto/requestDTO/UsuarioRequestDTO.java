package dev.nadsonaguiar.SistemaContabilize.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    private String nome;
    private String email;

}
