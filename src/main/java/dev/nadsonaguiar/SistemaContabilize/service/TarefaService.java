package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoMovimentacao;
import dev.nadsonaguiar.SistemaContabilize.dto.TarefaDashboardDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.TarefaRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.TarefaResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.TarefaDashboardMapper;
import dev.nadsonaguiar.SistemaContabilize.mapper.TarefaMapper;
import dev.nadsonaguiar.SistemaContabilize.mapper.UsuarioMapper;
import dev.nadsonaguiar.SistemaContabilize.model.*;
import dev.nadsonaguiar.SistemaContabilize.repository.MovimentacaoRepository;
import dev.nadsonaguiar.SistemaContabilize.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final AtividadeService atividadeService;
    private final ProcessoService processoService;
    private final UsuarioService usuarioService;
    private final TarefaMapper tarefaMapper;
    private final TarefaDashboardMapper tarefaDashboardMapper;


    public TarefaService(TarefaRepository tarefaRepository,
                         MovimentacaoRepository movimentacaoRepository,
                         AtividadeService atividadeService,
                         ProcessoService processoService,
                         UsuarioService usuarioService,
                         TarefaMapper tarefaMapper,
                         TarefaDashboardMapper tarefaDashboardMapper){
        this.tarefaRepository = tarefaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.atividadeService = atividadeService;
        this.processoService = processoService;
        this.usuarioService = usuarioService;
        this.tarefaMapper = tarefaMapper;
        this.tarefaDashboardMapper = tarefaDashboardMapper;
    }
    // Cria uma nova tarefa e registra movimentação no histórico

    public TarefaResponseDTO criarTarefa(TarefaRequestDTO dto, UsuarioModel criador) {
        // Busca as entidades relacionadas
        AtividadeModel atividade = atividadeService.buscarPorId(dto.getAtividadeId());
        ProcessoModel processo = processoService.buscarPorId(dto.getProcessoId());
        UsuarioModel responsavel = usuarioService.buscarPorId(dto.getResponsavelId());

        // Converte DTO para Model
        TarefaModel tarefa = tarefaMapper.toModel(dto,atividade,processo,responsavel,criador);
        // Salva a tarefa (dataCriacao e status são preenchidos pelo @PrePersist)
        TarefaModel tarefaSalva =  tarefaRepository.save(tarefa);
        // Registra a movimentação no histórico do processo
        MovimentacaoModel movimentacao = new MovimentacaoModel();
        movimentacao.setProcesso(processo);
        movimentacao.setTarefa(tarefaSalva);
        movimentacao.setTipo(TipoMovimentacao.TAREFA_CRIADA);
        movimentacao.setDescricao(
            String.format("Tarefa '%s' criada e atribuída para %s",
                atividade.getNome(),
                responsavel.getNome())
        );
        movimentacao.setData(tarefaSalva.getDataCriacao());
        movimentacao.setUsuario(criador);

        movimentacaoRepository.save(movimentacao);

        return tarefaMapper.toResponseDTO(tarefaSalva);
    }

    //  Busca tarefas para o dashboard do usuário(tarefas abertas, incluindo atrasadas)
    public List<TarefaDashboardDTO> buscarTarefasDashboard(Long usuarioId) {
        List<StatusTarefa> statusDashboard = List.of(StatusTarefa.ABERTO);

        List<TarefaModel> tarefas = tarefaRepository
            .findByResponsavelIdAndStatusInOrderByDataPrevistaAsc(
                usuarioId,
                statusDashboard
            );
        return tarefaDashboardMapper.toDTOList(tarefas);
    }

    // Conclui uma tarefa (sem criar movimentação)
   public TarefaResponseDTO concluirTarefa(Long tarefaId, UsuarioModel usuario){
        TarefaModel tarefa = tarefaRepository.findById(tarefaId)
                        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (tarefa.getStatus() == StatusTarefa.CONCLUIDA){
                throw new RuntimeException("Tarefa já está concluída e não pode ser reaberta");
        }
       // Atualiza a tarefa
       tarefa.setStatus(StatusTarefa.CONCLUIDA);
        tarefa.setDataConclusao(LocalDateTime.now());

        TarefaModel tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toResponseDTO(tarefaAtualizada);
    }
    // Busca tarefa por ID
    public TarefaModel buscarPorId(Long id){
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    // Busca tarefa por ID e retorna DTO
    public TarefaResponseDTO buscarPorIdDto(Long id){
        TarefaModel model = buscarPorId(id);
        return tarefaMapper.toResponseDTO(model);
    }

    // Lista todas as tarefas
    public List<TarefaResponseDTO> listarTodas(){
        List<TarefaModel> tarefas = tarefaRepository.findAll();
        return tarefaMapper.toResponseDTOList(tarefas);
    }






}
