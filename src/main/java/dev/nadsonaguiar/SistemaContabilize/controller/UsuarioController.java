package dev.nadsonaguiar.SistemaContabilize.controller;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.UsuarioRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.UsuarioResponseDTO;
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
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Criar usuário",
            description = "Cria um novo usuário do sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(
        @Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = usuarioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(
            summary = "Busca todos os usuários",
            description = "Retorna os dados de todos os usuários"
    )
    @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }


    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado",
                    content = @Content(
                            schema = @Schema(implementation = UsuarioResponseDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable("id") Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorIdDto(id);
            return ResponseEntity.ok(usuario);
    }


    @Operation(
            summary = "Atualiza um usuário por ID",
            description = "Atualiza um usuário especifico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable("id") Long id,
            @Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = usuarioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }
}
