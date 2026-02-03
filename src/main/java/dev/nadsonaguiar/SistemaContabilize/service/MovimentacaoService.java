package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.MovimentacaoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.MovimentacaoMapper;
import dev.nadsonaguiar.SistemaContabilize.model.MovimentacaoModel;
import dev.nadsonaguiar.SistemaContabilize.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, MovimentacaoMapper movimentacaoMapper) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper     = movimentacaoMapper;
    }

    // Busca movimentações de um processo
    public List<MovimentacaoResponseDTO> buscarProcesso(Long processoId){
        List<MovimentacaoModel> movimentacoes = movimentacaoRepository
            .findByProcessoIdOrderByDataDesc(processoId);
        return movimentacaoMapper.toResponseDTOList(movimentacoes);
    }

    // Busca movimentações de uma tarefa específica
    public List<MovimentacaoResponseDTO> buscarPorTarefa(Long tarefaId){
        List<MovimentacaoModel> movimentacoes = movimentacaoRepository
            .findByTarefaIdOrderByDataDesc(tarefaId);
        return movimentacaoMapper.toResponseDTOList(movimentacoes);
    }

}
