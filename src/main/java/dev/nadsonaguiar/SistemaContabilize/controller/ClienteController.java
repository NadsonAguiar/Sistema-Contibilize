package dev.nadsonaguiar.SistemaContabilize.controller;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ClienteRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ClienteResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.service.ClienteService;
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
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes do sistema")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @Operation(
            summary = "Cria um cliente",
            description = "Cria um novo cliente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO responseDTO = clienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }



    @Operation(
            summary = "Lista todos os clientes",
            description = "Retorna todos os clientes do sistema"
    )
    @ApiResponse(responseCode = "200", description = "Clientes encontrados")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos(){
        List<ClienteResponseDTO> response = clienteService.listarTodos();
        return ResponseEntity.ok(response);
    }



    @Operation(
            summary = "Busca cliente por ID",
            description = "Retorna os detalhes de um cliente específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscar(
            @Parameter(description = "ID do cliente", required = true)
            @PathVariable("id") Long id){
        ClienteResponseDTO responseDTO = clienteService.buscarPorIdDto(id);
            return ResponseEntity.ok(responseDTO);
    }



    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza um cliente específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
        @Parameter(description = "ID do cliente a ser atualizado", required = true)
        @PathVariable("id") Long id,
        @Valid @RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO responseDTO = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }



    @Operation(
            summary = "Deleta um cliente por ID",
            description = "Deleta um cliente que não tem processo ativo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Não contém cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> deletar(
        @Parameter(description = "ID do cliente",  required = true)
        @PathVariable("id") Long id){
        ClienteResponseDTO responseDTO = clienteService.buscarPorIdDto(id);
        if(responseDTO != null){
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
