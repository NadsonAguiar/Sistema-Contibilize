package dev.nadsonaguiar.SistemaContabilize.model;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "tb_tarefa")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private AtividadeModel atividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private ProcessoModel processo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id", nullable = false)
    private UsuarioModel responsavel;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_prevista")
    private LocalDateTime dataPrevista;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "status",  nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criada_por_id", nullable = false)
    private UsuarioModel criador;


    // Metodo chamado automaticamente antes de persistir
    @PrePersist
    public void onCreate(){
        this.dataCriacao = LocalDateTime.now();

        // Se o status não foi definido, inicia como ABERTO
        if(this.status == null){
            this.status = StatusTarefa.ABERTO;
        }
    }


    public StatusTarefa getStatusAtual(){
        // Se já está concluída, mantém concluída
        if(this.status == StatusTarefa.CONCLUIDA){
            return StatusTarefa.CONCLUIDA;
        }

        // Se passou da data prevista e continua aberta, está atrasada
        if( this.dataPrevista != null &&
            LocalDateTime.now().isAfter(this.dataPrevista) &&
            this.status == StatusTarefa.ABERTO){
            return StatusTarefa.ATRASADA;
        }
        // Caso contrário, retorna o status atual
        return this.status;
    }
    //  Metodo para verificar se está atrasada
    public boolean isAtrasada(){
        return getStatusAtual() == StatusTarefa.ATRASADA;
    }


}
