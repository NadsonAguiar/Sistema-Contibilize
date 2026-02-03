package dev.nadsonaguiar.SistemaContabilize.controller;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ProcessoRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoDetalheResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import dev.nadsonaguiar.SistemaContabilize.service.ProcessoService;
import dev.nadsonaguiar.SistemaContabilize.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
@Tag(name = "Processos", description = "Gerenciamento de processos do sistema")
public class ProcessoController {
    private final ProcessoService processoService;
    private final UsuarioService usuarioService;

    public ProcessoController(ProcessoService processoService, UsuarioService usuarioService) {
        this.processoService = processoService;
        this.usuarioService  = usuarioService;
    }

    @Operation(
            summary = "Cria um processo",
            description = "Cria um processo novo no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Processo criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ProcessoResponseDTO> criar(
        @Valid @RequestBody ProcessoRequestDTO dto){
        // TODO: Quando implementar Spring Security, pegar o usuário logado
        // Por enquanto, você pode:
        // 1. Receber o ID do criador no request
        // 2. Ou mockar um usuário fixo para teste
        UsuarioModel criador = obterUsuarioLogado();

        ProcessoResponseDTO responseDTO = processoService.criar(dto, criador);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }



    @Operation(
            summary = "Busca todos os processos",
            description = "Retorna todos os processos ativos"
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @GetMapping
    public ResponseEntity<List<ProcessoResponseDTO>> listarTodos() {
        List<ProcessoResponseDTO> processos = processoService.listarTodos();
        return ResponseEntity.ok(processos);
    }



    @Operation(
            summary = "Busca processo por ID",
            description = "Retorna os detalhes de um processo específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo encontrado"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProcessoResponseDTO> buscarPorId(
        @Parameter(description = "ID do processo", required = true)
        @PathVariable("id") Long id) {
        ProcessoResponseDTO processo = processoService.buscarPorIdDto(id);
            return ResponseEntity.ok(processo);
    }


    @Operation(
            summary = "Busca os detalhes de um processo pelo ID",
            description = "Retorna os detalhes de um processo específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalhes encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalhes não encontrado")
    })
    @GetMapping("/{id}/detalhes")
    public ResponseEntity<ProcessoDetalheResponseDTO> buscarDetalhes(
        @Parameter(description = "ID do processo", required = true)
        @PathVariable("id") Long id) {
        ProcessoDetalheResponseDTO detalhes = processoService.buscarDetalhes(id);
        return ResponseEntity.ok(detalhes);
    }


    @Operation(
            summary = "Busca o processo de um cliente por ID",
            description = "Retorna os detalhes do processo de um cliente específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ProcessoResponseDTO>> buscarPorCliente(
        @Parameter(description = "ID do cliente", required = true)
        @PathVariable("clienteId") Long clienteId) {
        List<ProcessoResponseDTO> processos = processoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(processos);
    }


    private UsuarioModel obterUsuarioLogado(){
        // Opção 1: Mockar para teste (use um ID de usuário que existe no seu banco)
        return usuarioService.buscarPorId(1L);
        // Opção 2: Com Spring Security (futuro)
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // return (UsuarioModel) auth.getPrincipal();
    }
}
