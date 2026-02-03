package dev.nadsonaguiar.SistemaContabilize.model;


import dev.nadsonaguiar.SistemaContabilize.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "tb_usuario")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "criador")
    private List<ProcessoModel> processosCriados;

    @OneToMany(mappedBy = "usuario")
    private List<MovimentacaoModel> movimentacoes;

    @OneToMany(mappedBy = "responsavel")
    private List<TarefaModel> tarefas;

    @OneToMany(mappedBy = "criador")
    private List<TarefaModel> tarefasCriadas;

}
