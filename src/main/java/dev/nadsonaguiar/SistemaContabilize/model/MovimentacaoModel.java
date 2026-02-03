package dev.nadsonaguiar.SistemaContabilize.model;

import dev.nadsonaguiar.SistemaContabilize.Enum.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "tb_movimentacao")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id",  nullable = false)
    private ProcessoModel processo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarefa_id")
    private TarefaModel tarefa;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data")
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",  nullable = false)
    private UsuarioModel usuario;

}
