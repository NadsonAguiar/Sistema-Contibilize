package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.Enum.Role;
import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.UsuarioRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.UsuarioResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.UsuarioMapper;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import dev.nadsonaguiar.SistemaContabilize.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    // Cria um usuário
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto){
        UsuarioModel model = usuarioMapper.toModel(dto);
        model.setRole(Role.FUNCIONARIO);
        UsuarioModel saved = usuarioRepository.save(model);
        return usuarioMapper.toResponseDTO(saved);
    }

    // Lista todos os usuários
    public List<UsuarioResponseDTO> listarTodos(){
        List<UsuarioModel> model = usuarioRepository.findAll();
        return usuarioMapper.toResponseDTOList(model);
    }

    // Busca usuário por ID
    public UsuarioModel buscarPorId(Long id){
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Busca usuário por ID e retorna DTO
    public UsuarioResponseDTO buscarPorIdDto(Long id){
        UsuarioModel model = buscarPorId(id);
        return usuarioMapper.toResponseDTO(model);
    }

    // Atualiza dados de um usuário
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto){
        UsuarioModel model = buscarPorId(id);
        usuarioMapper.updateModel(dto, model);
        UsuarioModel update = usuarioRepository.save(model);
        update.setId(id);
        return usuarioMapper.toResponseDTO(update);
    }

}
