package dev.nadsonaguiar.SistemaContabilize.controller;

import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.MovimentacaoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@Tag(name = "Movimentações", description = "Gerenciamento de movimentações do sistema")
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Operation(
            summary = "Busca uma movimentação de um processo",
            description = "Retorna uma movimentação específica de um processo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimentação encontrada"),
            @ApiResponse(responseCode = "404", description = "Movimentação não encontrada")
    })
    @GetMapping("/processo/{processoId}")
    public ResponseEntity<List<MovimentacaoResponseDTO>> buscarPorProcesso(
        @Parameter(description = "ID do processo", required = true)
        @PathVariable("processoId") Long processoId) {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.buscarProcesso(processoId);
        return ResponseEntity.ok(movimentacoes);
    }


    @Operation(
            summary = "Busca a movimentação  de uma tarefa por ID",
            description = "Retorna a movimentação de uma tarefa específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/tarefa/{tarefaId}")
    public ResponseEntity<List<MovimentacaoResponseDTO>> buscarPorTarefa(
        @Parameter(description = "ID de uma tarefa", required = true)
        @PathVariable("tarefaId") Long tarefaId) {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.buscarPorTarefa(tarefaId);
        return ResponseEntity.ok(movimentacoes);
    }
}
