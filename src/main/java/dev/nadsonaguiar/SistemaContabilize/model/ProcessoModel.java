package dev.nadsonaguiar.SistemaContabilize.model;


import dev.nadsonaguiar.SistemaContabilize.Enum.TipoProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "tb_processo")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",  nullable = false)
    private ClienteModel cliente;

    @Column(name = "tipo_processo")
    @Enumerated(EnumType.STRING)
    private TipoProcesso tipoProcesso;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por", nullable = false)
    private UsuarioModel criador;

    @OneToMany(mappedBy = "processo")
    private List<TarefaModel> tarefas;

    @OneToMany(mappedBy = "processo")
    private List<MovimentacaoModel> movimentacoes;

}
