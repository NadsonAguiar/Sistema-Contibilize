package dev.nadsonaguiar.SistemaContabilize.controller;


import dev.nadsonaguiar.SistemaContabilize.dto.TarefaDashboardDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ProcessoRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.TarefaRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.TarefaResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.UsuarioResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.TarefaModel;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import dev.nadsonaguiar.SistemaContabilize.service.TarefaService;
import dev.nadsonaguiar.SistemaContabilize.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas dos processos")
public class TarefaController {

    private final TarefaService tarefaService;
    private final UsuarioService usuarioService;

    public TarefaController(TarefaService tarefaService, UsuarioService usuarioService) {
        this.tarefaService = tarefaService;
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Cria uma nova tarefa",
            description = """
            Cria uma nova tarefa vinculada a um processo e a uma atividade.
            A tarefa nasce com status ABERTO.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = TarefaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou relacionamentos não encontrados")
    })
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(
        @Valid @RequestBody TarefaRequestDTO requestDTO){
        UsuarioModel criador = obterUsuarioLogado();
        TarefaResponseDTO response = tarefaService.criarTarefa(requestDTO, criador);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response);
    }

    @Operation(
            summary = "Listar todas as tarefas",
            description = "Lista todas as tarefas criadas"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodas() {
        List<TarefaResponseDTO> tarefas = tarefaService.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @Operation(
            summary = "Buscar atividade por ID",
            description = "Retorna os detalhes de uma tarefa específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(
            @Parameter(description = "ID da tarefa", required = true)
            @PathVariable("id") Long id) {
        TarefaResponseDTO tarefa = tarefaService.buscarPorIdDto(id);
        return ResponseEntity.ok(tarefa);
    }


    @Operation(
            summary = "Buscar dashboard do usuário",
            description = "Retorna tarefas abertas e atrasadas de um usuário específico, ordenadas por data prevista"
    )
    @ApiResponse(responseCode = "200", description = "Dashboard retornado com sucesso")
    @GetMapping("/dashboard/{usuarioId}")
    public ResponseEntity<List<TarefaDashboardDTO>> buscarDashboard(
        @Parameter(description = "ID do usuário", required = true)
        @PathVariable("usuarioId") Long usuarioId) {
        List<TarefaDashboardDTO> tarefas = tarefaService.buscarTarefasDashboard(usuarioId);
        return ResponseEntity.ok(tarefas);
    }


    @Operation(
            summary = "Buscar dashboard do usuário",
            description = "Retorna tarefas abertas e atrasadas de um usuário específico, ordenadas por data prevista"
    )
    @ApiResponse(responseCode = "200", description = "Dashboard retornado com sucesso")
    @GetMapping("/meu-dashboard")
    public ResponseEntity<List<TarefaDashboardDTO>> buscarMeuDashboard() {
        UsuarioModel usuarioLogado = obterUsuarioLogado();
        List<TarefaDashboardDTO> tarefas = tarefaService.buscarTarefasDashboard(usuarioLogado.getId());
        return ResponseEntity.ok(tarefas);
    }

    @Operation(
            summary = "Concluir tarefa",
            description = "Marca uma tarefa como concluída. Tarefas concluídas não podem ser reabertas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa concluída com sucesso"),
            @ApiResponse(responseCode = "400", description = "Tarefa já concluída ou não encontrada")
    })
    @PutMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponseDTO> concluir(
        @Parameter(description = "ID da tarefa", required = true)
        @PathVariable("id") Long id){
        UsuarioModel criador = obterUsuarioLogado();
        TarefaResponseDTO responseDTO = tarefaService.concluirTarefa(id, criador);
        return ResponseEntity.ok(responseDTO);
    }

    private UsuarioModel obterUsuarioLogado(){
        return usuarioService.buscarPorId(1L);
    }


}
