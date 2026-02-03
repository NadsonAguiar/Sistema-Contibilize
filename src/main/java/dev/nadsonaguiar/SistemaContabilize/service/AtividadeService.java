package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.AtividadeRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.AtividadeResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.AtividadeMapper;
import dev.nadsonaguiar.SistemaContabilize.model.AtividadeModel;
import dev.nadsonaguiar.SistemaContabilize.repository.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final AtividadeMapper atividadeMapper;

    public AtividadeService(AtividadeRepository atividadeRepository, AtividadeMapper atividadeMapper) {
        this.atividadeRepository = atividadeRepository;
        this.atividadeMapper     = atividadeMapper;
    }

    // Cria uma nova atividade
    public AtividadeResponseDTO criar(AtividadeRequestDTO dto){
        AtividadeModel model = atividadeMapper.toModel(dto);
        AtividadeModel saved = atividadeRepository.save(model);
        return atividadeMapper.toResponseDTO(saved);
    }

    // Lista todas as atividades
    public List<AtividadeResponseDTO> listarTodas(){
        List<AtividadeModel> atividades = atividadeRepository.findAll();
        return atividadeMapper.toResponseDTOList(atividades);
    }

    // Busca atividade por ID
    public AtividadeModel buscarPorId(Long id){
        return atividadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
    }

    // Busca atividade por ID e retorna DTO
    public AtividadeResponseDTO buscarPorIdDto(Long id){
        AtividadeModel atividade = buscarPorId(id);
        return atividadeMapper.toResponseDTO(atividade);
    }

}
