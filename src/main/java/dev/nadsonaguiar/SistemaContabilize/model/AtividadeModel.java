package dev.nadsonaguiar.SistemaContabilize.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "tb_atividade")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "atividade")
    private List<TarefaModel> tarefas;

}
