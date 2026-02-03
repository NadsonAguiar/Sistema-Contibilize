package dev.nadsonaguiar.SistemaContabilize.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Table(name = "tb_cliente")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<ProcessoModel> processos;

}
