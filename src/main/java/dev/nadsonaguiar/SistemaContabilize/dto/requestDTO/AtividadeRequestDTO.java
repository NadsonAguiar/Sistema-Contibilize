package dev.nadsonaguiar.SistemaContabilize.dto.requestDTO;


import jakarta.validation.constraints.NotBlank;

public record AtividadeRequestDTO(@NotBlank(message = "Nome é obrigatório") String nome) {

}
