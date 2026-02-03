package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import dev.nadsonaguiar.SistemaContabilize.Enum.TipoMovimentacao;
import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ProcessoRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.MovimentacaoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoDetalheResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.TarefaResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.MovimentacaoMapper;
import dev.nadsonaguiar.SistemaContabilize.mapper.ProcessoMapper;
import dev.nadsonaguiar.SistemaContabilize.mapper.TarefaMapper;
import dev.nadsonaguiar.SistemaContabilize.model.*;
import dev.nadsonaguiar.SistemaContabilize.repository.MovimentacaoRepository;
import dev.nadsonaguiar.SistemaContabilize.repository.ProcessoRepository;
import dev.nadsonaguiar.SistemaContabilize.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProcessoService {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final ProcessoMapper processoMapper;
    private final MovimentacaoMapper movimentacaoMapper;
    private final TarefaMapper tarefaMapper;
    private final ProcessoRepository processoRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final TarefaRepository tarefaRepository;

    public ProcessoService(ProcessoRepository processoRepository, ClienteService clienteService, UsuarioService usuarioService, MovimentacaoRepository movimentacaoRepository, TarefaRepository tarefaRepository, ProcessoMapper processoMapper, MovimentacaoMapper movimentacaoMapper, TarefaMapper tarefaMapper) {
        this.processoRepository     = processoRepository;
        this.clienteService         = clienteService;
        this.usuarioService         = usuarioService;
        this.movimentacaoRepository = movimentacaoRepository;
        this.tarefaRepository       = tarefaRepository;
        this.processoMapper         = processoMapper;
        this.movimentacaoMapper     = movimentacaoMapper;
        this.tarefaMapper           = tarefaMapper;
    }

     // Busca um processo com todas as suas movimentações (histórico).
    public ProcessoModel buscarProcessoComHistorico(Long processoId){
        ProcessoModel processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        // Força o carregamento das movimentações (se usar LAZY)
        processo.getMovimentacoes().size();
        return processo;
    }
    // Cria um processo e registra a primeira movimentação
    public ProcessoResponseDTO criar(ProcessoRequestDTO dto, UsuarioModel criador){
        // Valida se o cliente existe
        ClienteModel cliente = clienteService.buscarPorId(dto.getClienteId());
        // Cria o processo
        ProcessoModel processo = processoMapper.toModel(dto,cliente,criador);
        ProcessoModel processoSalvo = processoRepository.save(processo);

        // Cria a primeira movimentação (PROCESSO_CRIADO)
        MovimentacaoModel movimentacao = new MovimentacaoModel();
        movimentacao.setProcesso(processoSalvo);
        movimentacao.setTarefa(null); // Criação de processo não tem tarefa
        movimentacao.setTipo(TipoMovimentacao.PROCESSO_CRIADO);
        movimentacao.setDescricao(
            String.format("Processo '%s' criado para o cliente %s",
                processoSalvo.getTipoProcesso(),
                cliente.getNome())
        );
        movimentacao.setData(processoSalvo.getDataCriacao());
        movimentacao.setUsuario(criador);
        movimentacaoRepository.save(movimentacao);

        return processoMapper.toResponseDTO(processoSalvo);
    }

    // Lista todos os processos
    public List<ProcessoResponseDTO> listarTodos(){
        List<ProcessoModel> models = processoRepository.findAll();
        return processoMapper.toResponseDTOList(models);
    }

    // Busca processo por ID
    public ProcessoModel buscarPorId(Long id){
        return processoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
    }

    // Busca processo por ID e retorna DTO simples
    public ProcessoResponseDTO buscarPorIdDto(Long id){
        ProcessoModel model = buscarPorId(id);
        return processoMapper.toResponseDTO(model);
    }

    // Busca detalhes completos do processo (para tela de detalhes)
    public ProcessoDetalheResponseDTO buscarDetalhes(Long id){
        ProcessoModel processo = buscarPorId(id);
        // Busca todas as movimentações do processo
        List<MovimentacaoModel> movimentacoes = movimentacaoRepository
            .findByProcessoIdOrderByDataDesc(id);
        // Busca tarefas ativas (abertas ou atrasadas)
        List<TarefaModel> tarefas = tarefaRepository
            .findByProcessoIdAndStatusIn(id,
                List.of(StatusTarefa.ABERTO));
        // Converte para DTOs
        List<MovimentacaoResponseDTO> movimentacoesDTO =
            movimentacaoMapper.toResponseDTOList(movimentacoes);
        List<TarefaResponseDTO> tarefasDTO =
            tarefaMapper.toResponseDTOList(tarefas);

        return processoMapper.toDetalheResponseDTO(processo, movimentacoesDTO, tarefasDTO);
    }

    // Busca processos por cliente
    public List<ProcessoResponseDTO> buscarPorCliente(Long clienteId){
        // Você precisa criar esse metodo no repository
        List<ProcessoModel> processos = processoRepository.findByClienteId(clienteId);
        return processoMapper.toResponseDTOList(processos);
    }
}
