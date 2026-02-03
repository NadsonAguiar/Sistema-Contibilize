package dev.nadsonaguiar.SistemaContabilize.controller;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.AtividadeRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.AtividadeResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.service.AtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@Tag(name = "Atividades", description = "Gerenciamento de atividades do sistema")
public class AtividadeController {

    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    // Criar atividade
    @Operation(
            summary = "Criar nova atividade",
            description = "Cria uma nova atividade no catálogo do sistema")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Atividade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<AtividadeResponseDTO> criar(
        @Valid @RequestBody AtividadeRequestDTO request) {
        AtividadeResponseDTO response = atividadeService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }

    // Buscar atividades
    @Operation(
            summary = "Listar todas as atividades",
            description = "Retorna uma lista com todas as atividades cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<AtividadeResponseDTO>> buscarTodos(){
        List<AtividadeResponseDTO> response = atividadeService.listarTodas();
        return ResponseEntity.ok(response);
    }

    // Buscar atividade por ID
    @Operation(
            summary = "Buscar atividade por ID",
            description = "Retorna os detalhes de uma atividade específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atividade encontrada"),
            @ApiResponse(responseCode = "400", description = "Atividade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> buscar(
            @Parameter(description = "ID da atividade", required = true)
            @PathVariable("id") Long id){
        AtividadeResponseDTO response = atividadeService.buscarPorIdDto(id);
            return ResponseEntity.ok(response);
    }
}
